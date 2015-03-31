package com.pureland.core.service.battle.impl;

import java.util.List;

import com.pureland.common.db.data.battle.SkillConsume;
import com.pureland.common.service.battle.SkillConsumeCommonService;
import com.pureland.common.service.battle.impl.SkillConsumeCommonServiceImpl;
import org.apache.commons.collections.CollectionUtils;

import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.battle.ArmyConsumeCommonService;
import com.pureland.common.service.battle.ResourceRecordCommonService;
import com.pureland.common.service.battle.impl.ArmyConsumeCommonServiceImpl;
import com.pureland.common.service.battle.impl.AttackCommonServiceImpl;
import com.pureland.common.service.battle.impl.ResourceRecordCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.battle.AttackService;

public class AttackServiceImpl extends AttackCommonServiceImpl implements AttackService {
	private ArmyConsumeCommonService armyConsumeCommonService = (ArmyConsumeCommonService) SpringContextUtil.getBean(ArmyConsumeCommonServiceImpl.class
			.getSimpleName());
	private ResourceRecordCommonService recordCommonService = (ResourceRecordCommonService) SpringContextUtil.getBean(ResourceRecordCommonServiceImpl.class
			.getSimpleName());
	private SkillConsumeCommonService skillConsumeCommonService = (SkillConsumeCommonService) SpringContextUtil.getBean(SkillConsumeCommonServiceImpl.class
			.getSimpleName());

	@Override
	public Long addAttackResult(Attack attack) throws CoreException {
		List<ArmyConsume> usedArmies = attack.getUsedArmies();
		List<ResourceRecord> stolenResources = attack.getStolenResources();
		List<SkillConsume> skillConsumeList = attack.getUsedSkills();
		Long attackId = super.addAttack(attack);

		if (attackId != null && CollectionUtils.isNotEmpty(usedArmies)) {
			List<ArmyConsume> armyConsumes = ArmyConsume.updateArmyConsume(attackId, BattleType.ATTACK, usedArmies);
			armyConsumeCommonService.addBatchArmyConsume(armyConsumes);
		}
		if (attackId != null && CollectionUtils.isNotEmpty(stolenResources)) {
			List<ResourceRecord> resourceRecords = ResourceRecord.updateResourceRecord(attackId, BattleType.ATTACK, stolenResources);
			recordCommonService.addBatchResourceRecord(resourceRecords);
		}
		if (attackId != null && CollectionUtils.isNotEmpty(stolenResources)) {
			List<SkillConsume> skillConsumes = SkillConsume.updateSkillConsume(attackId, BattleType.ATTACK, skillConsumeList);
			skillConsumeCommonService.addBatchSkillConsume(skillConsumes);
		}
		return attackId;
	}

	@Override
	public List<Attack> getAttacks(Long userRaceId) throws CoreException {
		return super.getAttacks(userRaceId);
	}

}
