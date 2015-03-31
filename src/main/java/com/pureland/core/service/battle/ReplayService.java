package com.pureland.core.service.battle;

import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface ReplayService {

	public Long addRelay(Long battleId, Replay replay) throws CoreException;
	
	public Long getReplayId(Long userRaceId, Long battleId, BattleType battleType) throws CoreException;
	
	public Replay getReplay(Long replayId) throws CoreException;
	
}
