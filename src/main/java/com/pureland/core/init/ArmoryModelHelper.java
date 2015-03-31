package com.pureland.core.init;

import com.google.common.collect.*;
import com.pureland.common.db.dao.daoInterface.ArmoryModelDAOInterface;
import com.pureland.common.db.dao.mysql.ArmoryModelDAO;
import com.pureland.common.db.error.DBException;
import com.pureland.common.db.statics.ArmoryModel;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/22.
 */
public class ArmoryModelHelper {

    private ArmoryModelDAOInterface armoryModelDAO = (ArmoryModelDAOInterface) SpringContextUtil.getBean(ArmoryModelDAO.class.getSimpleName());

    public static Map<Integer, Multimap<Integer, String>> ENTITIES = Maps.newHashMap();

    public void init() {
        try {
            for (Integer raceId : armoryModelDAO.queryArmoryModeForMap().keySet()) {
                List<ArmoryModel> list = armoryModelDAO.queryArmoryModeForMap().get(raceId);
                Multimap<Integer, String> map1 = ENTITIES.get(raceId);
                if (map1 == null) {
                    map1 = ArrayListMultimap.create();
                    ENTITIES.put(raceId, map1);
                }
                for (ArmoryModel am : list) {
                    Integer buildType = am.getBuildType();
                    map1.put(buildType, am.getBaseId().toString());
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
