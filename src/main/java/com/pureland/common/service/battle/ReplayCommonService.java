package com.pureland.common.service.battle;

import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface ReplayCommonService {

	public Long addRelay(Replay replay) throws CoreException;
	
	public Replay getReplay(Long id) throws CoreException;
}
