package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/9.
 */
public class ExploreGatherServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.COMPLETE.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s \n", ShipStateEnum.COMPLETE.name(), ShipStateEnum.values()[ship.getState()]);
        }

        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        UserExt userExt = userRace.getUserExt();

        boolean isFull = false;
        Map<String, Resource> resourceMap = ship.getResourcesForMap();
        //判断是否仓库已经满了
        for (Resource resource : resourceMap.values()) {
            ResourceServerTypeEnum resourceServerType = resource.getResourceType();
            if (resourceServerType == ResourceServerTypeEnum.Diamond) continue;
            Resource hasResource = userExt.getResourcesForMap().get(resourceServerType);
            int canGetCount = resource.getCount();
            int max = buildingCommonService.getResourceUpperLimit(userRaceId, resourceServerType) - hasResource.getCount();
            if (canGetCount > max) {
                isFull = true;
                break;
            }
        }

        if (!isFull) {
            //给玩家增加资源
            for (Resource resource : resourceMap.values()) {
                ResourceServerTypeEnum resourceServerType = resource.getResourceType();
                Resource hasResource = userExt.getResourcesForMap().get(resourceServerType);
                int canGetCount = resource.getCount();
                hasResource.setCount(hasResource.getCount() + canGetCount);
                resourceCommonService.updateResource(hasResource);
            }

            //设置状态
            ship.setState(ShipStateEnum.IDLE.ordinal());
            super.updateShip(ship);
        }

        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }
}
