package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Ship;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;

import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class CancelExploreServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.EXPLORING.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s \n", ShipStateEnum.EXPLORING.name(), ShipStateEnum.values()[ship.getState()]);
        }

        //更改状态
        ship.setState(ShipStateEnum.IDLE.ordinal());
        //修改结束时间
        ship.setEndTime(0L);

        super.updateShip(ship);
        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }
}
