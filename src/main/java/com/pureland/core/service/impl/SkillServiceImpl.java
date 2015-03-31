package com.pureland.core.service.impl;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.impl.SkillCommonServiceImpl;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.SkillService;

import org.apache.commons.lang3.StringUtils;

/**
 * @author qinpeirong
 */
public class SkillServiceImpl extends SkillCommonServiceImpl implements SkillService {

    @Override
    public void trainingSkill(final Long userRaceId, final Integer cid, Integer amount)
            throws CoreException {
        try {
            String keyId = Skill.generatorIdKey(new String[]{userRaceId.toString(), cid.toString()});
            String skillId = RString.get(keyId);
            if (StringUtils.isEmpty(skillId)) {
                super.addSkill(userRaceId, cid, amount, amount);
            } else {
                Skill skill = super.getSkill(Long.parseLong(skillId));
                Integer amount2 = skill.getAmount() + amount;
                skill.setAmount(amount2);
                super.updateSkill(skill);
            }
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }

    @Override
    public void upgradeSkill(Long userRaceId, Integer cid) throws CoreException {
        try {
            String keyId = Skill.generatorIdKey(new String[]{userRaceId.toString(), cid.toString()});
            String skillId = RString.get(keyId);
            EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
            Integer upgradeId = entityModel.getUpgradeId();
            Skill skill = new Skill();
            skill.setId(Long.parseLong(skillId));
            skill.setCid(upgradeId);
            super.updateSkill(skill);
        } catch (RedisException e) {
            throw new CoreException(e);
        }

    }

}
