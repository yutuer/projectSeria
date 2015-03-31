package com.pureland.common.db.dao.redis.battle;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;

public class DefendDAO extends RedisDAO {
    public Long addDefend(Defend defend) throws DBException {
        Long id = defend.getId();
        try {
            if (id == null) {
                id = RString.generator(Entity.DEFEND.getName());
            }
            String keyUserRaceId = Defend.generatorFieldKey(id, Entity.Defend.USERRACEID.getName());
            String keyPercentage = Defend.generatorFieldKey(id, Entity.Defend.PERCENTAGE.getName());
            String keyStar = Defend.generatorFieldKey(id, Entity.Defend.STAR.getName());
            String keyUseDonatedArmy = Defend.generatorFieldKey(id, Entity.Defend.USEDONATEDARMY.getName());
            String keyRewardMedal = Defend.generatorFieldKey(id, Entity.Defend.REWARDMEDAL.getName());
            String keyRewardCrown = Defend.generatorFieldKey(id, Entity.Defend.REWARDCROWN.getName());
            String keyRewardGoldByCrownLevel = Defend.generatorFieldKey(id, Entity.Defend.REWARDGOLDBYCROWNLEVEL.getName());
            String keyRewardOilByCrownLevel = Defend.generatorFieldKey(id, Entity.Defend.REWARDOILBYCROWNLEVEL.getName());
            String keyTimestamp = Defend.generatorFieldKey(id, Entity.Defend.TIMESTAMP.getName());
            String keyPeerId = Defend.generatorFieldKey(id, Entity.Defend.PEERID.getName());
            String keyPeerName = Defend.generatorFieldKey(id, Entity.Defend.PEERNAME.getName());

            RString.set(keyUserRaceId, String.valueOf(defend.getUserRaceId()));
            RString.set(keyPercentage, String.valueOf(defend.getPercentage()));
            RString.set(keyStar, String.valueOf(defend.getStar()));
            RString.set(keyUseDonatedArmy, String.valueOf(defend.getUseDonatedArmy() == true ? 1 : 0));
            RString.set(keyRewardMedal, String.valueOf(defend.getRewardMedal()));
            RString.set(keyRewardCrown, String.valueOf(defend.getRewardCrown()));
            RString.set(keyRewardGoldByCrownLevel, String.valueOf(defend.getRewardGoldByCrownLevel()));
            RString.set(keyRewardOilByCrownLevel, String.valueOf(defend.getRewardOilByCrownLevel()));
            RString.set(keyTimestamp, String.valueOf(defend.getTimestamp()));
            RString.set(keyPeerId, String.valueOf(defend.getPeerId()));
            RString.set(keyPeerName, defend.getPeerName());

            addSetCollection(defend.getUserRaceId(), String.valueOf(id));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return id;
    }

    public Defend getDefend(Long id) throws DBException {
        Defend defend = new Defend();
        try {
            String keyUserRaceId = Defend.generatorFieldKey(id, Entity.Defend.USERRACEID.getName());
            String keyPercentage = Defend.generatorFieldKey(id, Entity.Defend.PERCENTAGE.getName());
            String keyStar = Defend.generatorFieldKey(id, Entity.Defend.STAR.getName());
            String keyUseDonatedArmy = Defend.generatorFieldKey(id, Entity.Defend.USEDONATEDARMY.getName());
            String keyRewardMedal = Defend.generatorFieldKey(id, Entity.Defend.REWARDMEDAL.getName());
            String keyRewardCrown = Defend.generatorFieldKey(id, Entity.Defend.REWARDCROWN.getName());
            String keyRewardGoldByCrownLevel = Defend.generatorFieldKey(id, Entity.Defend.REWARDGOLDBYCROWNLEVEL.getName());
            String keyRewardOilByCrownLevel = Defend.generatorFieldKey(id, Entity.Defend.REWARDOILBYCROWNLEVEL.getName());
            String keyTimestamp = Defend.generatorFieldKey(id, Entity.Defend.TIMESTAMP.getName());
            String keyPeerId = Defend.generatorFieldKey(id, Entity.Defend.PEERID.getName());
            String keyPeerName = Defend.generatorFieldKey(id, Entity.Defend.PEERNAME.getName());

            defend.setId(id);
            defend.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
            defend.setPercentage(Integer.parseInt(RString.get(keyPercentage)));
            defend.setStar(Integer.parseInt(RString.get(keyStar)));
            String useDonatedArmy = RString.get(keyUseDonatedArmy);
            if (StringUtils.isNotEmpty(useDonatedArmy)) {
                defend.setUseDonatedArmy("1".equals(useDonatedArmy) ? true : false);
            }
            defend.setRewardMedal(Integer.parseInt(RString.get(keyRewardMedal)));
            defend.setRewardCrown(Integer.parseInt(RString.get(keyRewardCrown)));
            defend.setRewardGoldByCrownLevel(Integer.parseInt(RString.get(keyRewardGoldByCrownLevel)));
            defend.setRewardOilByCrownLevel(Integer.parseInt(RString.get(keyRewardOilByCrownLevel)));
            defend.setTimestamp(Long.parseLong(RString.get(keyTimestamp)));
            defend.setPeerId(Long.parseLong(RString.get(keyPeerId)));
            defend.setPeerName(RString.get(keyPeerName));
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return defend;
    }

    public void addSetCollection(Long userRaceId, String value) throws DBException {
        super.addSetCollection(Entity.DEFEND, String.valueOf(userRaceId), value);
    }

    public Set<String> getSetCollection(Long userRaceId) throws DBException {
        return super.getSetCollection(Entity.DEFEND, String.valueOf(userRaceId));
    }
}
