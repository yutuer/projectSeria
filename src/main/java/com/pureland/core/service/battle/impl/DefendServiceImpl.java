package com.pureland.core.service.battle.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.battle.ArmyConsumeCommonService;
import com.pureland.common.service.battle.ResourceRecordCommonService;
import com.pureland.common.service.battle.impl.ArmyConsumeCommonServiceImpl;
import com.pureland.common.service.battle.impl.DefendCommonServiceImpl;
import com.pureland.common.service.battle.impl.ResourceRecordCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.battle.DefendService;
/**
 * 
 * @author qinpeirong
 *
 */
public class DefendServiceImpl extends DefendCommonServiceImpl implements DefendService {
	private ArmyConsumeCommonService armyConsumeCommonService = (ArmyConsumeCommonService) SpringContextUtil.getBean(ArmyConsumeCommonServiceImpl.class.getSimpleName());
	private ResourceRecordCommonService recordCommonService = (ResourceRecordCommonService) SpringContextUtil.getBean(ResourceRecordCommonServiceImpl.class.getSimpleName());

	@Override
	public Long addDefendResult(Defend defend) throws CoreException {
		List<ArmyConsume> usedArmies = defend.getUsedArmies();
		List<ResourceRecord> stolenResources = defend.getStolenResources();
		Long defendId = super.addDefend(defend);
		if(defendId != null && CollectionUtils.isNotEmpty(usedArmies)) {
			List<ArmyConsume> armyConsumes = ArmyConsume.updateArmyConsume(defendId, BattleType.DEFEND, usedArmies);
			armyConsumeCommonService.addBatchArmyConsume(armyConsumes);
		}
		if(defendId != null && CollectionUtils.isNotEmpty(stolenResources)) {
			List<ResourceRecord> resourceRecords = ResourceRecord.updateResourceRecord(defendId, BattleType.DEFEND, stolenResources);
			recordCommonService.addBatchResourceRecord(resourceRecords);
		}
		return defendId;
	}

	@Override
	public List<Defend> getDefends(Long userRaceId) throws CoreException {
		return super.getDefends(userRaceId);
	}

}
