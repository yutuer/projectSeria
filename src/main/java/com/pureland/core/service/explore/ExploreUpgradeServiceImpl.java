package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.util.parseExcel.MyUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.core.init.EntityModelHelper;

import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class ExploreUpgradeServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {
    private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();
        ResourceServerTypeEnum consumeResourceServerType = exploreBean.getConsumeResourceServerType();
        //判断客户端传来的类型正不正确
        if (consumeResourceServerType != ResourceServerTypeEnum.Gold && consumeResourceServerType != ResourceServerTypeEnum.Oil) {
            throw new CoreException("升级时候资源类型不正确,inType:%s", consumeResourceServerType.name());
        }

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.IDLE.ordinal() && ship.getState() != ShipStateEnum.COMPLETE.ordinal()) {
            throw new CoreException("状态不正确,需要%s或者%s, 现在%s \n", ShipStateEnum.IDLE.name(), ShipStateEnum.COMPLETE.name(), ShipStateEnum.values()[ship.getState()]);
        }

        int cid = ship.getCid();
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
        if (entityModel.getUpgradeId() == null || entityModel.getUpgradeId().intValue() == 0) {
            throw new CoreException("不能升级");
        }

        EntityModel upgradeModel = EntityModelHelper.ENTITIES.get(entityModel.getUpgradeId());
        int consumeCount = upgradeModel.getCostResourceCount();

        //判断玩家货币够不够
        UserExt userExt = userExtCommonService.getUserExt(userRaceId);
        Resource resource = userExt.getResourcesForMap().get(consumeResourceServerType);
        if (consumeCount > resource.getCount()) {
            throw new CoreException("消费不足!! 需要:%d,有:%d", consumeCount, resource.getCount());
        }

        //根据cid去查询表,获得升级结束时间
        long upgradeTime = upgradeModel.getBuildTime() * MyUtil.SECOND;
        long endTime = System.currentTimeMillis() + upgradeTime;
        ship.setEndTime(endTime);

        //设置资源
        Resource consumeResource = new Resource();
        consumeResource.setResourceType(consumeResourceServerType);
        consumeResource.setCount(consumeCount);
        ship.setUpgradeCostResource(consumeResource);

        //设置状态
        ship.setState(ShipStateEnum.UPGRADING.ordinal());
        super.updateShip(ship);

        //玩家扣钱
        resource.setCount(resource.getCount() - consumeCount);
        resourceCommonService.updateResource(resource);

        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }
}
