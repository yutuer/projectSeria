package com.pureland.common.service.impl;

import com.google.common.collect.Lists;
import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.SkillDAO;
import com.pureland.common.db.data.Skill;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.SkillCommonService;
import com.pureland.common.util.SpringContextUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author qinpeirong
 */
public class SkillCommonServiceImpl extends BaseService implements SkillCommonService {
    protected SkillDAO skillDAO = (SkillDAO) SpringContextUtil.getBean(SkillDAO.class.getSimpleName());

    @Override
    public Long addSkill(Long userRaceId, Integer cid, Integer amount, Integer totalAmount)
            throws CoreException {
        Skill skill = new Skill();
        skill.setUserRaceId(userRaceId);
        skill.setCid(cid);
        skill.setAmount(amount);
        skill.setTotalAmount(totalAmount);

        Long id = null;
        try {
            id = skillDAO.addSkill(skill);
        } catch (DBException e) {
            error(e);
        }
        return id;
    }

    @Override
    public Skill getSkill(Long skillId) throws CoreException {
        if (skillId == null) {
            throw new CoreException("armyId is null");
        }

        Skill skill = null;
        try {
            skill = skillDAO.getSkill(skillId);
        } catch (DBException e) {
            error(e);
        }

        return skill;
    }

    @Override
    public List<Skill> getSkills(Long userRaceId) throws CoreException {
        List<Skill> skills = Lists.newArrayList();
        try {
            Set<String> skillIds = skillDAO.getSetCollection(userRaceId);
            Iterator<String> iterator = skillIds.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                Skill skill = skillDAO.getSkill(Long.parseLong(next));
                skills.add(skill);
            }
        } catch (DBException e) {
            error(e);
        }
        return skills;
    }

    @Override
    public void updateSkill(Skill skill) throws CoreException {
        try {
            skillDAO.updateSkill(skill);
        } catch (DBException e) {
            error(e);
        }
    }

    @Override
    public void upgradeAllSkills(Long userRaceId, Integer skillCid, Integer upgradeId) throws CoreException {
        List<Skill> skills = getSkills(userRaceId);
        for (Skill skill : skills) {
            if (skill.getCid().intValue() == skillCid) {
                skill.setCid(upgradeId);
                updateSkill(skill);
            }
        }
    }

    @Override
    public void updateBattleSkill(List<Skill> skills) throws CoreException {
        try {
            for (Skill skillConsume : skills) {
                Long userRaceId = skillConsume.getUserRaceId();
                Integer cid = skillConsume.getCid();
                Integer amount = skillConsume.getAmount();

                String keyId = Skill.generatorIdKey(new String[]{userRaceId.toString(), cid.toString()});
                String skillId = RString.get(keyId);
                Skill skill = getSkill(Long.valueOf(skillId));
                skill.setAmount(skill.getAmount() - amount);
                updateSkill(skill);
            }
        } catch (RedisException e) {
            error(e);
        }
    }

}
