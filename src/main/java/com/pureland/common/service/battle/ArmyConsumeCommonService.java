package com.pureland.common.service.battle;

import java.util.List;

import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;

public interface ArmyConsumeCommonService {

	public Long addArmyConsume(ArmyConsume armyConsume) throws CoreException; 
	
	public void addBatchArmyConsume(List<ArmyConsume> armyConsumes) throws CoreException;
	
	public List<ArmyConsume> getArmyConsumes(Long userRaceId, Long battleId, BattleType battleType) throws CoreException;
}
