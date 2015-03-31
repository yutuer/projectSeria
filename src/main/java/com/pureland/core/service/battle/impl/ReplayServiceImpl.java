package com.pureland.core.service.battle.impl;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.battle.impl.ReplayCommonServiceImpl;
import com.pureland.core.service.battle.ReplayService;

public class ReplayServiceImpl extends ReplayCommonServiceImpl implements ReplayService {

	@Override
	public Long addRelay(Long battleId, Replay replay) throws CoreException {
		if(replay == null) {
			return null;
		}
		replay.setBattleId(battleId);
		return super.addRelay(replay);
	}

	@Override
	public Long getReplayId(Long userRaceId, Long battleId, BattleType battleType)
			throws CoreException {
		StringBuffer singleMark = new StringBuffer();
		singleMark.append(userRaceId)
		          .append(Entity.SEPARATOR)
		          .append(battleId)
		          .append(Entity.SEPARATOR)
		          .append(battleType.getId());
		String keyId = Replay.generatorIdKey(singleMark.toString());
		try {
			String replayId = RString.get(keyId);
			if(StringUtils.isNotEmpty(replayId) && !"null".equals(replayId)) {
				return Long.parseLong(replayId);
			}
		} catch (RedisException e) {
			throw new CoreException(e);
		}
		return null;
	}

	@Override
	public Replay getReplay(Long replayId) throws CoreException {
		return super.getReplay(replayId);
	}

}
