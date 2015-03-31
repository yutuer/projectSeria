package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.db.statics.ExploreModel;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.util.parseExcel.MyUtil;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.init.ExploreModelHelper;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Administrator on 2015/3/9.
 */
public class ExploreServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.IDLE.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s \n", ShipStateEnum.IDLE.name(), ShipStateEnum.values()[ship.getState()]);
        }

        Integer cid = ship.getCid();
        EntityModel em = EntityModelHelper.ENTITIES.get(cid);
        int shipLevel = em.getLevel();
        Integer section = exploreBean.getSection();
        //根据等级和section获得explore时间
        ExploreModel exploreModel = ExploreModelHelper.ENTITIES.get(section);
        try {
            Field field = ExploreModel.class.getDeclaredField("lv" + shipLevel);
            field.setAccessible(true);
            int endSecond = (Integer) field.get(exploreModel);
            long endTime = System.currentTimeMillis() + MyUtil.SECOND * endSecond / MyUtil.HOUR;
            ship.setEndTime(endTime);
        } catch (NoSuchFieldException e) {
            throw new CoreException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new CoreException(e.getMessage());
        }

        //根据cid, section 查询出结束时间
        ship.setSection(section);
        //修改状态
        ship.setState(ShipStateEnum.EXPLORING.ordinal());

        super.updateShip(ship);
        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }


}
