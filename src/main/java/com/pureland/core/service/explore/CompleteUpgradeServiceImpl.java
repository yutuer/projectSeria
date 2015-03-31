package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Ship;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.util.GameUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class CompleteUpgradeServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.UPGRADING.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s \n", ShipStateEnum.UPGRADING.name(), ShipStateEnum.values()[ship.getState()]);
        }

        Long endTime = ship.getEndTime();
        Long now = System.currentTimeMillis();
        if (GameUtil.IsTimeWrong(now, endTime)) {
            throw new CoreException("完成时间不正确!!");
        }

        Integer cid = ship.getCid();
        //   查询表,获得升级后的id
        int upgradeCid = ship.getCid() + 1;
        ship.setCid(upgradeCid);

        //将升级资源设置为null
        ship.setUpgradeCostResource(null);
        //设置状态
        super.setStateAfterUpgradeCompleteOrCancel(ship);

        updateShip(ship);

        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }
}
