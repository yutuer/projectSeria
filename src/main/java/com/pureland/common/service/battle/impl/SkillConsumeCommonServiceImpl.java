package com.pureland.common.service.battle.impl;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.battle.ArmyConsumeDAO;
import com.pureland.common.db.dao.redis.battle.SkillConsumeDAO;
import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.SkillConsumeCommonService;
import com.pureland.common.util.SpringContextUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SkillConsumeCommonServiceImpl extends BaseService implements SkillConsumeCommonService {
    private SkillConsumeDAO skillConsumeDAO = (SkillConsumeDAO) SpringContextUtil.getBean(SkillConsumeDAO.class.getSimpleName());

    @Override
    public Long addSkillConsume(SkillConsume skillConsume) throws CoreException {
        try {
            return skillConsumeDAO.addSkillConsume(skillConsume);
        } catch (DBException e) {
            throw new CoreException(e);
        }
    }

    @Override
    public void addBatchSkillConsume(List<SkillConsume> skillConsumes)
            throws CoreException {
        for (SkillConsume skillConsume : skillConsumes) {
            addSkillConsume(skillConsume);
        }

    }

    @Override
    public List<SkillConsume> getSkillConsumes(Long userRaceId, Long battleId, BattleType battleType)
            throws CoreException {
        List<SkillConsume> skillConsumes = Lists.newArrayList();
        try {
            Set<String> skillConsumeIds = skillConsumeDAO.getSetCollection(userRaceId, battleId, battleType);
            Iterator<String> iterator = skillConsumeIds.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                long id = Long.parseLong(next);
                SkillConsume skillConsume = skillConsumeDAO.getSkillConsume(id);
                skillConsumes.add(skillConsume);
            }
        } catch (DBException e) {
            throw new CoreException(e);
        }
        return skillConsumes;
    }

}
