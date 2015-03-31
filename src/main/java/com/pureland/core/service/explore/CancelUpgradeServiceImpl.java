package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;

import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class CancelUpgradeServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.UPGRADING.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s", ShipStateEnum.UPGRADING.name(), ShipStateEnum.values()[ship.getState()]);
        }

        //还给玩家资源
        Resource upgradeCostResource = ship.getUpgradeCostResource();
        if (upgradeCostResource == null) {
            throw new CoreException("upgradeCostResource == null");
        }

        UserExt userExt = userExtCommonService.getUserExt(userRaceId);
        Resource resource = userExt.getResourcesForMap().get(upgradeCostResource.getResourceType());
        resource.setCount(resource.getCount() + (int) (upgradeCostResource.getCount() / 2));
        resourceCommonService.updateResource(resource);

        //将升级资源设置为null
        ship.setUpgradeCostResource(null);
        //更改状态
        setStateAfterUpgradeCompleteOrCancel(ship);
        //修改结束时间
        ship.setEndTime(0L);

        super.updateShip(ship);
        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }
}
