package com.pureland.common.service.impl;

import com.pureland.common.db.dao.redis.ResourceDAO;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.helper.ResourceHelper;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;
import java.util.Map;

/**
 * @author qinpeirong
 */
public class ResourceCommonServiceImpl implements ResourceCommonService {
    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
    private ResourceDAO resourceDAO = (ResourceDAO) SpringContextUtil.getBean(ResourceDAO.class.getSimpleName());

    @Override
    public void gather(Long userRaceId, Long buildingSid, Long gatherTime,
                       ResourceServerTypeEnum resourceType, Integer resourceCount)
            throws CoreException {
        //TODO 不能超过服务器时间

        long nowTime = System.currentTimeMillis();
        if (gatherTime > nowTime) {
            gatherTime = nowTime;
        }

        String buildingId = Building.getIdKeyString(userRaceId, buildingSid);
        Building building = buildingCommonService.getBuilding(buildingId);
        EntityModel entityModel = building.getEntityModel();
        //TODO 校验建筑类型和传入的类型
//        if(entityModel.)

        if (!resourceType.name().equals(entityModel.getResourceType())) {
            throw new CoreException(String.format("建筑类型和传入的类型 不匹配:%s, %s", resourceType.name(), entityModel.getResourceType()));
        }

        //到现在为止产出的数量
        int canGetCount = ResourceHelper.getBuildTrueResource(building, gatherTime);

        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        UserExt userExt = userRace.getUserExt();
        Resource resource = userExt.getResourcesForMap().get(resourceType);
        Integer count = resource.getCount();

        // 获得当前最大能放入的值
        int max = buildingCommonService.getResourceUpperLimit(userRaceId, resourceType) - count;
        //收集完之后建筑还剩下的值(不管怎么样都设置为0)
        int leftCount = 0;
        // 应该给玩家添加的值
        int addCount = canGetCount;
        if (canGetCount > max) {
            leftCount = canGetCount - max;
            addCount = max;
        }

        // 校验真正能添加的值是否和客户端一致
        if (addCount != resourceCount) {
            throw new CoreException(String.format("真正能添加的值是否和客户端不一致: %d, %d", addCount, resourceCount));
        }

        building.setStorageCount(leftCount);
        building.setGatherTime(gatherTime);

        buildingCommonService.updateBuilding(building);

        count += addCount;
        resource.setCount(count);
        try {
            resourceDAO.updateResource(resource);
        } catch (DBException e) {
            throw new CoreException(e.getMessage());
        }
    }

    @Override
    public void updateBattleResouce(Long userRaceId, List<Resource> resouces) throws CoreException {
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        UserExt userExt = userRace.getUserExt();
        Long userExtId = userExt.getId();
        Map<ResourceServerTypeEnum, Resource> resourcesForMap = userExt.getResourcesForMap();

        for (Resource resource : resouces) {
            ResourceServerTypeEnum resourceType = resource.getResourceType();
            Integer count = resource.getCount();

            Resource dbResource = resourcesForMap.get(resourceType);
            try {
                if (dbResource != null) {
                    Integer dbCount = dbResource.getCount();
                    dbResource.setCount(dbCount + count);
                    resourceDAO.updateResource(dbResource);
                } else {
                    resource.setUserExtId(userExtId);
                    resourceDAO.addResource(resource);
                }
            } catch (DBException e) {
                throw new CoreException(e);
            }

        }
    }

    @Override
    public void updateResource(Resource resource) throws CoreException {
        try {
            resourceDAO.updateResource(resource);
        } catch (DBException e) {
            throw new CoreException(e);
        }
    }

}
