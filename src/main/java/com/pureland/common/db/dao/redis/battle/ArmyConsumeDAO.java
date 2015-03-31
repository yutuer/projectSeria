package com.pureland.common.db.dao.redis.battle;

import java.util.Set;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.ArmyConsume;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.enums.Entity;

public class ArmyConsumeDAO extends RedisDAO {

	public Long addArmyConsume(ArmyConsume armyConsume) throws DBException {
		Long id = armyConsume.getId();
		try {
			if(id == null) {
				id = RString.generator(Entity.ARMYCONSUME.getName());
			}
			String keyUserRaceId = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.USERRACEID.getName());
			String keyBattleId = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.BATTLEID.getName());
			String keyCid = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.CID.getName());
			String keyAmount = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.AMOUNT.getName());
			
			RString.set(keyUserRaceId, String.valueOf(armyConsume.getUserRaceId()));
			RString.set(keyBattleId, String.valueOf(armyConsume.getBattleId()));
			RString.set(keyCid, String.valueOf(armyConsume.getCid()));
			RString.set(keyAmount, String.valueOf(armyConsume.getAmount()));
			addSetCollection(armyConsume.getUserRaceId(), armyConsume.getBattleId(), armyConsume.getBattleType(), String.valueOf(id));
			
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return id;
	}
	
	public ArmyConsume getArmyConsume(Long id) throws DBException {
		ArmyConsume armyConsume = new ArmyConsume();
		try {
			String keyUserRaceId = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.USERRACEID.getName());
			String keyBattleId = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.BATTLEID.getName());
			String keyCid = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.CID.getName());
			String keyAmount = ArmyConsume.generatorFieldKey(id, Entity.ArmyConsume.AMOUNT.getName());
			
			armyConsume.setId(id);
			armyConsume.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
			armyConsume.setBattleId(Long.parseLong(RString.get(keyBattleId)));
			armyConsume.setCid(Integer.parseInt(RString.get(keyCid)));
			armyConsume.setAmount(Integer.parseInt(RString.get(keyAmount)));
			
		} catch(RedisException e) {
			throw new DBException(e.getMessage());
		}
		return armyConsume;
	}
	
	public void addSetCollection(Long userRaceId, Long battleId, BattleType battleType, String value) throws DBException {
		StringBuffer filed = new StringBuffer();
		filed.append(userRaceId)
		     .append(Entity.SEPARATOR)
		     .append(battleId)
		     .append(Entity.SEPARATOR)
		     .append(battleType.getId());
		super.addSetCollection(Entity.ATTACK, filed.toString(), value);
	}
	
	public Set<String> getSetCollection(Long userRaceId, Long battleId, BattleType battleType) throws DBException {
		StringBuffer filed = new StringBuffer();
		filed.append(userRaceId)
		     .append(Entity.SEPARATOR)
		     .append(battleId)
		     .append(Entity.SEPARATOR)
		     .append(battleType.getId());
		return super.getSetCollection(Entity.ATTACK, filed.toString());
	}
}
