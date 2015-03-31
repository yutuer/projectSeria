package com.pureland.core.service;

import com.pureland.common.db.data.Army;
import com.pureland.common.error.CoreException;

import java.util.List;

/**
 * 
 * @author qinpeirong
 *
 */
public interface ArmyService {

	public void trainingArmy(Long userRaceId, Integer cid, Integer amount) throws CoreException;
	
	public void upgradeArmy(Long userRaceId, Integer cid) throws CoreException;

    public void updateBattleArmy(List<Army> armies) throws CoreException;
}
