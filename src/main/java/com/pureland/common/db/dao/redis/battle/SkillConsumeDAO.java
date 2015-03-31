package com.pureland.common.db.dao.redis.battle;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;

import java.util.Set;

public class SkillConsumeDAO extends RedisDAO {

    public Long addSkillConsume(SkillConsume skillConsume) throws DBException {
        Long id = skillConsume.getId();
        try {
            if (id == null) {
                id = RString.generator(Entity.SKILLCONSUME.getName());
            }

            String keyUserRaceId = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.USERRACEID.getName());
            String keyBattleId = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.BATTLEID.getName());
            String keyCid = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.CID.getName());
            String keyAmount = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.AMOUNT.getName());

            RString.set(keyUserRaceId, String.valueOf(skillConsume.getUserRaceId()));
            RString.set(keyBattleId, String.valueOf(skillConsume.getBattleId()));
            RString.set(keyCid, String.valueOf(skillConsume.getCid()));
            RString.set(keyAmount, String.valueOf(skillConsume.getAmount()));
            addSetCollection(skillConsume.getUserRaceId(), skillConsume.getBattleId(), skillConsume.getBattleType(), String.valueOf(id));
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
        return id;
    }

    public SkillConsume getSkillConsume(Long id) throws DBException {
        SkillConsume skillConsume = new SkillConsume();
        try {
            String keyUserRaceId = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.USERRACEID.getName());
            String keyBattleId = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.BATTLEID.getName());
            String keyCid = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.CID.getName());
            String keyAmount = SkillConsume.generatorFieldKey(id, Entity.SkillConsume.AMOUNT.getName());

            skillConsume.setId(id);
            skillConsume.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
            skillConsume.setBattleId(Long.parseLong(RString.get(keyBattleId)));
            skillConsume.setCid(Integer.parseInt(RString.get(keyCid)));
            skillConsume.setAmount(Integer.parseInt(RString.get(keyAmount)));
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
        return skillConsume;
    }

    public void addSetCollection(Long userRaceId, Long battleId, BattleType battleType, String value) throws DBException {
        StringBuffer field = new StringBuffer();
        field.append(userRaceId)
                .append(Entity.SEPARATOR)
                .append(battleId)
                .append(Entity.SEPARATOR)
                .append(battleType.getId());
        super.addSetCollection(Entity.ATTACK, field.toString(), value);
    }

    public Set<String> getSetCollection(Long userRaceId, Long battleId, BattleType battleType) throws DBException {
        StringBuffer field = new StringBuffer();
        field.append(userRaceId)
                .append(Entity.SEPARATOR)
                .append(battleId)
                .append(Entity.SEPARATOR)
                .append(battleType.getId());
        return super.getSetCollection(Entity.ATTACK, field.toString());
    }
}
