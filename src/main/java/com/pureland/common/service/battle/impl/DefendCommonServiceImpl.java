package com.pureland.common.service.battle.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.service.battle.SkillConsumeCommonService;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.battle.DefendDAO;
import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.ArmyConsumeCommonService;
import com.pureland.common.service.battle.DefendCommonService;
import com.pureland.common.service.battle.ResourceRecordCommonService;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class DefendCommonServiceImpl extends BaseService implements
        DefendCommonService {
    private DefendDAO defendDAO = (DefendDAO) SpringContextUtil.getBean(DefendDAO.class.getSimpleName());
    private ArmyConsumeCommonService armyConsumeCommonService = (ArmyConsumeCommonService) SpringContextUtil.getBean(ArmyConsumeCommonServiceImpl.class.getSimpleName());
    private ResourceRecordCommonService resourceRecordCommonService = (ResourceRecordCommonService) SpringContextUtil.getBean(ResourceRecordCommonServiceImpl.class.getSimpleName());
    private SkillConsumeCommonService skillConsumeCommonService = (SkillConsumeCommonService) SpringContextUtil.getBean(SkillConsumeCommonServiceImpl.class.getSimpleName());

    @Override
    public Long addDefend(Defend defend) throws CoreException {
        try {
            return defendDAO.addDefend(defend);
        } catch (DBException e) {
            throw new CoreException(e);
        }
    }

    @Override
    public List<Defend> getDefends(Long userRaceId) throws CoreException {
        List<Defend> defends = Lists.newArrayList();

        try {
            Set<String> defendIds = defendDAO.getSetCollection(userRaceId);
            Iterator<String> iterator = defendIds.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (StringUtils.isNotEmpty(next)) {
                    long defendId = Long.parseLong(next);
                    Defend defend = defendDAO.getDefend(defendId);
                    List<ArmyConsume> armyConsumes = armyConsumeCommonService.getArmyConsumes(userRaceId, defendId, BattleType.DEFEND);
                    List<ResourceRecord> resourceRecords = resourceRecordCommonService.getResourceRecords(userRaceId, defendId, BattleType.DEFEND);
                    List<SkillConsume> skillConsumeList = skillConsumeCommonService.getSkillConsumes(userRaceId, defendId, BattleType.ATTACK);
                    defend.setUsedArmies(armyConsumes);
                    defend.setStolenResources(resourceRecords);
                    defend.setUsedSkills(skillConsumeList);
                    defends.add(defend);
                }
            }

        } catch (DBException e) {
            throw new CoreException(e);
        }
        return defends;
    }

}
