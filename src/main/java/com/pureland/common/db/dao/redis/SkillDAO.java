package com.pureland.common.db.dao.redis;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;

import java.util.Set;

/**
 * @author qinpeirong
 */
public class SkillDAO extends RedisDAO {

    private String TAG = PurelandLog.getClassTag(SkillDAO.class);

    public Long addSkill(Skill skill) throws DBException {
        Long userRaceId = skill.getUserRaceId();
        Integer cid = skill.getCid();
        Integer amount = skill.getAmount();
        Integer totalAmount = skill.getTotalAmount();

        try {
            Long id = RString.generator(Entity.SKILL.getName());
            String keyId = Skill.generatorIdKey(new String[]{userRaceId.toString(), cid.toString()});
            String keyUserRaceId = Skill.generatorFieldKey(id, Entity.Skill.USERRACEID.getName());
            String keyCid = Skill.generatorFieldKey(id, Entity.Skill.CID.getName());
            String keyAmount = Skill.generatorFieldKey(id, Entity.Skill.AMOUNT.getName());
            String keyTotalAmount = Skill.generatorFieldKey(id, Entity.Skill.TOTALAMOUNT.getName());

            RString.set(keyId, String.valueOf(id));
            RString.set(keyUserRaceId, String.valueOf(userRaceId));
            RString.set(keyCid, String.valueOf(cid));
            RString.set(keyAmount, String.valueOf(amount));
            RString.set(keyTotalAmount, String.valueOf(totalAmount));

            addSetCollection(userRaceId, String.valueOf(id));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return null;
    }

    public Skill getSkill(Long SkillId) throws DBException {
        Skill Skill = new Skill();

        try {
            String keyUserRaceId = Skill.generatorFieldKey(SkillId, Entity.Skill.USERRACEID.getName());
            String keyCid = Skill.generatorFieldKey(SkillId, Entity.Skill.CID.getName());
            String keyAmount = Skill.generatorFieldKey(SkillId, Entity.Skill.AMOUNT.getName());
            String keyTotalAmount = Skill.generatorFieldKey(SkillId, Entity.Skill.TOTALAMOUNT.getName());

            Skill.setId(SkillId);
            Skill.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
            Skill.setCid(Integer.parseInt(RString.get(keyCid)));
            Skill.setAmount(Integer.parseInt(RString.get(keyAmount)));
            Skill.setTotalAmount(Integer.parseInt(RString.get(keyTotalAmount)));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return Skill;
    }

    public void updateSkill(Skill Skill) throws DBException {
        Long id = Skill.getId();
        Integer cid = Skill.getCid();
        Integer amount = Skill.getAmount();
        Integer totalAmount = Skill.getTotalAmount();

        if (id == null) {
            throw new DBException("");
        }

        try {
            if (cid != null) {
                String keyCid = Skill.generatorFieldKey(id, Entity.Skill.CID.getName());
                RString.set(keyCid, String.valueOf(cid));
            }

            if (amount != null) {
                String keyAmount = Skill.generatorFieldKey(id, Entity.Skill.AMOUNT.getName());
                RString.set(keyAmount, String.valueOf(amount));
            }

            if (totalAmount != null) {
                String keyTotalAmount = Skill.generatorFieldKey(id, Entity.Skill.TOTALAMOUNT.getName());
                RString.set(keyTotalAmount, String.valueOf(totalAmount));
            }

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void addSetCollection(Long userRaceId, String value) throws DBException {
        super.addSetCollection(Entity.SKILL, String.valueOf(userRaceId), value);
    }

    public Set<String> getSetCollection(Long userRaceId) throws DBException {
        return super.getSetCollection(Entity.SKILL, String.valueOf(userRaceId));
    }
}
