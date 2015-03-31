package com.pureland.common.service.battle;

import java.util.List;

import com.pureland.common.db.data.battle.Defend;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface DefendCommonService {

	public Long addDefend(Defend defend) throws CoreException;
	
	public List<Defend> getDefends(Long userRaceId) throws CoreException;
}
