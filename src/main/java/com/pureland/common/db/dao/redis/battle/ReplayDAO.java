package com.pureland.common.db.dao.redis.battle;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.Replay;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;

public class ReplayDAO extends RedisDAO {

	public Long addRelay(Replay replay) throws DBException {
		Long id = replay.getId();
		try {
			if(id == null) {
				id = RString.generator(Entity.REPLAY.getName());
			}
			StringBuffer singleMark = new StringBuffer();
			singleMark.append(replay.getUserRaceId())
			          .append(Entity.SEPARATOR)
			          .append(replay.getBattleId())
			          .append(Entity.SEPARATOR)
			          .append(replay.getBattleType().getId());
			
			String keyId = Replay.generatorIdKey(singleMark.toString());
			String keyUserRaceId = Replay.generatorFieldKey(id, Entity.Replay.USERRACEID.getName());
			String keyBattleId = Replay.generatorFieldKey(id, Entity.Replay.BATTLEID.getName());
			String keyBattleType = Replay.generatorFieldKey(id, Entity.Replay.BATTLETYPE.getName());
			String keyReplay = Replay.generatorFieldKey(id, Entity.Replay.REPLAY.getName());
			
			RString.set(keyId, String.valueOf(id));
			RString.set(keyUserRaceId, String.valueOf(replay.getUserRaceId()));
			RString.set(keyBattleId, String.valueOf(replay.getBattleId()));
			BattleType battleType = replay.getBattleType();
			RString.set(keyBattleType, String.valueOf(battleType.getId()));
			RString.set(keyReplay.getBytes(), replay.getReplayByte());
			
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return id;
	}
	
	public Replay getReplay(Long id) throws DBException {
		Replay replay = new Replay();
		try {
			String keyUserRaceId = Replay.generatorFieldKey(id, Entity.Replay.USERRACEID.getName());
			String keyBattleId = Replay.generatorFieldKey(id, Entity.Replay.BATTLEID.getName());
			String keyBattleType = Replay.generatorFieldKey(id, Entity.Replay.BATTLETYPE.getName());
			String keyReplay = Replay.generatorFieldKey(id, Entity.Replay.REPLAY.getName());
			
			replay.setId(id);
			replay.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
			replay.setBattleId(Long.parseLong(RString.get(keyBattleId)));
			String battleTypeId = RString.get(keyBattleType);
			if(StringUtils.isNotEmpty(battleTypeId)) {
				BattleType battleType = BattleType.getBattleTypeById(Long.parseLong(battleTypeId));
				replay.setBattleType(battleType);
			}
			replay.setReplayByte(RString.get(keyReplay.getBytes()));
			
		} catch(Exception e) {
			throw new DBException(e.getMessage());
		}
		return replay;
	}
}
