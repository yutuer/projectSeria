package com.pureland.common.service.helper;

import java.util.Map;

import com.google.common.collect.Maps;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.RaceServerTypeEnum;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/3/10.
 */
public class ShipDbHelper {

    private static final Map<RaceServerTypeEnum, Integer> RACESHIPIDMAP = Maps.newHashMap();

    public static int GetShipIdByRace(RaceServerTypeEnum raceServerTypeEnum) {
        Integer shipId = RACESHIPIDMAP.get(raceServerTypeEnum);
        if (shipId == null) {
            for (EntityModel em : EntityModelHelper.ENTITIES.values()) {
                //种族相等, 等级等于1 ,类型为pve
                if (em.getRaceType().equals(raceServerTypeEnum.name()) && em.getLevel().intValue() == 1 && em.getSubType().equals(SubServerTypeEnum.PVE.name())) {
                    shipId = em.getBaseId();
                    break;
                }
            }
            RACESHIPIDMAP.put(raceServerTypeEnum, shipId);
        }
        return shipId;
    }

    public static Ship GetNewShip(Long userRaceId, RaceServerTypeEnum raceServerTypeEnum) {
        Ship ship = new Ship();
        ship.setUserRaceId(userRaceId);
        int shipId = GetShipIdByRace(raceServerTypeEnum);
        ship.setCid(shipId);
        ship.setSection(0);
        ship.setEndTime(0L);
        ship.setState(ShipStateEnum.IDLE.ordinal());
        return ship;
    }


}
