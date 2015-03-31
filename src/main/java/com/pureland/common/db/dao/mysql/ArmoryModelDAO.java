package com.pureland.common.db.dao.mysql;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.daoInterface.ArmoryModelDAOInterface;
import com.pureland.common.enums.RaceServerTypeEnum;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Maps;
import com.pureland.common.db.statics.ArmoryModel;
import com.pureland.common.util.GuavaFunctions;
import com.pureland.common.util.GuavaFunctions.GroupFunction;
import org.apache.ibatis.session.SqlSession;

public class ArmoryModelDAO extends MysqlDAO implements ArmoryModelDAOInterface {

    public List<ArmoryModel> queryArmoryModelList() {
        List<ArmoryModel> armories = Lists.newArrayList();
        SqlSession session = null;
        try {
            session = getSession();
            armories = session.selectList("queryArmoryModelList");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return armories;
    }

    @Override
    public Map<Integer, List<ArmoryModel>> queryArmoryModeForMap() {
        Map<Integer, List<ArmoryModel>> armoryMaps = Maps.newHashMap();
        List<ArmoryModel> armories = queryArmoryModelList();
        if (CollectionUtils.isNotEmpty(armories)) {
            List<List<ArmoryModel>> group = GuavaFunctions.group(armories, new GroupFunction<ArmoryModel>() {

                @Override
                public boolean sameGroup(ArmoryModel element1,
                                         ArmoryModel element2) {
                    Integer raceId1 = RaceServerTypeEnum.getRaceServerTypeEnumByName(element1.getRaceType()).ordinal();
                    Integer raceId2 = RaceServerTypeEnum.getRaceServerTypeEnumByName(element2.getRaceType()).ordinal();
                    if (raceId1.equals(raceId2)) {
                        return true;
                    }
                    return false;
                }

            });

            for (List<ArmoryModel> armoryModelList : group) {
                ArmoryModel armoryModel = armoryModelList.get(0);
                Integer raceId = RaceServerTypeEnum.getRaceServerTypeEnumByName(armoryModel.getRaceType()).ordinal();
                armoryMaps.put(raceId, armoryModelList);
            }
        }
        return armoryMaps;
    }
}
