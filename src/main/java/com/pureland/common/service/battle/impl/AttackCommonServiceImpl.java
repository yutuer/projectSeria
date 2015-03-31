package com.pureland.common.service.battle.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.service.battle.SkillConsumeCommonService;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.battle.AttackDAO;
import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.ArmyConsumeCommonService;
import com.pureland.common.service.battle.AttackCommonService;
import com.pureland.common.service.battle.ResourceRecordCommonService;
import com.pureland.common.util.SpringContextUtil;

public class AttackCommonServiceImpl extends BaseService implements
        AttackCommonService {
    private AttackDAO attackDAO = (AttackDAO) SpringContextUtil.getBean(AttackDAO.class.getSimpleName());
    private ArmyConsumeCommonService armyConsumeCommonService = (ArmyConsumeCommonService) SpringContextUtil.getBean(ArmyConsumeCommonServiceImpl.class.getSimpleName());
    private ResourceRecordCommonService resourceRecordCommonService = (ResourceRecordCommonService) SpringContextUtil.getBean(ResourceRecordCommonServiceImpl.class.getSimpleName());
    private SkillConsumeCommonService skillConsumeCommonService = (SkillConsumeCommonService) SpringContextUtil.getBean(SkillConsumeCommonServiceImpl.class.getSimpleName());

    @Override
    public Long addAttack(Attack attack) throws CoreException {
        Integer rewardMedal = attack.getRewardMedal();
        Integer rewardCrown = attack.getRewardCrown();
        Integer rewardGoldByCrownLevel = attack.getRewardGoldByCrownLevel();
        Integer rewardOilByCrownLevel = attack.getRewardOilByCrownLevel();
        attack.setRewardMedal(rewardMedal != null ? rewardMedal : 0);
        attack.setRewardCrown(rewardCrown != null ? rewardCrown : 0);
        attack.setRewardGoldByCrownLevel(rewardGoldByCrownLevel != null ? rewardGoldByCrownLevel : 0);
        attack.setRewardOilByCrownLevel(rewardOilByCrownLevel != null ? rewardOilByCrownLevel : 0);

        Long id = null;
        try {
            id = attackDAO.addAttack(attack);
        } catch (DBException e) {
            throw new CoreException(e);
        }
        return id;
    }

    @Override
    public List<Attack> getAttacks(Long userRaceId) throws CoreException {
        List<Attack> attacks = Lists.newArrayList();

        try {
            Set<String> attackIds = attackDAO.getSetCollection(userRaceId);
            Iterator<String> iterator = attackIds.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (StringUtils.isNotEmpty(next)) {
                    Long attackId = Long.parseLong(next);
                    Attack attack = attackDAO.getAttack(attackId);
                    List<ArmyConsume> armyConsumes = armyConsumeCommonService.getArmyConsumes(userRaceId, attackId, BattleType.ATTACK);
                    List<ResourceRecord> resourceRecords = resourceRecordCommonService.getResourceRecords(userRaceId, attackId, BattleType.ATTACK);
                    List<SkillConsume> skillConsumeList = skillConsumeCommonService.getSkillConsumes(userRaceId, attackId, BattleType.ATTACK);
                    attack.setUsedArmies(armyConsumes);
                    attack.setStolenResources(resourceRecords);
                    attack.setUsedSkills(skillConsumeList);
                    attacks.add(attack);
                }
            }

        } catch (DBException e) {
            throw new CoreException(e);
        }
        return attacks;
    }

}
