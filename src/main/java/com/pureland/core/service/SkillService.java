package com.pureland.core.service;

import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface SkillService {

	public void trainingSkill(Long userRaceId, Integer cid, Integer amount) throws CoreException;
	
	public void upgradeSkill(Long userRaceId, Integer cid) throws CoreException;


}
