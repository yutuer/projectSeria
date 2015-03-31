package com.pureland.core.service.battle;

import java.util.List;

import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface AttackService {

	public Long addAttackResult(Attack attack) throws CoreException;
	
	public List<Attack> getAttacks(Long userRaceId) throws CoreException;
}
