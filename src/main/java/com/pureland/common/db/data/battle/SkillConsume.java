package com.pureland.common.db.data.battle;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

import java.util.List;

/**
 * Created by Administrator on 2015/2/11.
 */
public class SkillConsume extends DataObject {

    private Long id;
    private Long userRaceId;
    private Long battleId;
    private BattleType battleType;
    private Integer cid;
    private Integer amount;

    public static String generatorFieldKey(Long id, String field) {
        return generatorFieldKey(Entity.ARMYCONSUME, id, field);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBattleId() {
        return battleId;
    }

    public void setBattleId(Long battleId) {
        this.battleId = battleId;
    }

    public BattleType getBattleType() {
        return battleType;
    }

    public void setBattleType(BattleType battleType) {
        this.battleType = battleType;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }

    public void setUserRaceId(Long userRaceId) {
        this.userRaceId = userRaceId;
    }

    public static List<SkillConsume> updateSkillConsume(final Long battleId, final BattleType battleType, List<SkillConsume> skillConsumes) {
        return Lists.transform(skillConsumes, new Function<SkillConsume, SkillConsume>() {
            @Override
            public SkillConsume apply(SkillConsume skillConsume) {
                skillConsume.setBattleId(battleId);
                skillConsume.setBattleType(battleType);
                return skillConsume;
            }
        });
    }
}
