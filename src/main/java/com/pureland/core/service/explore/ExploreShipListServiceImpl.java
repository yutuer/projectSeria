package com.pureland.core.service.explore;

import com.pureland.common.db.data.Ship;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/3/7.
 */
public class ExploreShipListServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    private ExploreLogicService completeExploreServiceImpl = (ExploreLogicService) SpringContextUtil.getBean(CompleteExploreServiceImpl.class.getSimpleName());
    private ExploreLogicService completeUpgradeServiceImpl = (ExploreLogicService) SpringContextUtil.getBean(CompleteUpgradeServiceImpl.class.getSimpleName());


    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        List<Ship> shipList = super.getShips(userRaceId);

        long now = System.currentTimeMillis();
        //检查是否升级完毕,或者探索完毕
        for (Ship ship : shipList) {
            if (ship.getState() == ShipStateEnum.EXPLORING.ordinal() || ship.getState() == ShipStateEnum.UPGRADING.ordinal()) {
                if (now > ship.getEndTime()) { //说明完成
                    exploreBean.setSid(ship.getSid());
                    //需要改变状态
                    if (ship.getState() == ShipStateEnum.EXPLORING.ordinal()) {
                        completeExploreServiceImpl.exploreLogic(exploreBean);
                    } else {
                        completeUpgradeServiceImpl.exploreLogic(exploreBean);
                    }
                }
            }
        }
        return shipList;
    }
}
