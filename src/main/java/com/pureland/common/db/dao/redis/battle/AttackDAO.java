package com.pureland.common.db.dao.redis.battle;

import java.util.Set;

import com.pureland.common.db.data.battle.Defend;
import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.battle.Attack;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
/**
 * 
 * @author qinpeirong
 *
 */
public class AttackDAO extends RedisDAO {
	
	public Long addAttack(Attack attack) throws DBException {
		Long id = attack.getId();
		try {
			if(id == null) {
				id = RString.generator(Entity.ATTACK.getName());
			}
			String keyUserRaceId = Attack.generatorFieldKey(id, Entity.Attack.USERRACEID.getName());
			String keyPercentage = Attack.generatorFieldKey(id, Entity.Attack.PERCENTAGE.getName());
			String keyStar = Attack.generatorFieldKey(id, Entity.Attack.STAR.getName());
			String keyUseDonatedArmy = Attack.generatorFieldKey(id, Entity.Attack.USEDONATEDARMY.getName());
			String keyRewardMedal = Attack.generatorFieldKey(id, Entity.Attack.REWARDMEDAL.getName());
			String keyRewardCrown = Attack.generatorFieldKey(id, Entity.Attack.REWARDCROWN.getName());
			String keyRewardGoldByCrownLevel = Attack.generatorFieldKey(id, Entity.Attack.REWARDGOLDBYCROWNLEVEL.getName());
			String keyRewardOilByCrownLevel = Attack.generatorFieldKey(id, Entity.Attack.REWARDOILBYCROWNLEVEL.getName());
			String keyTimestamp = Attack.generatorFieldKey(id, Entity.Attack.TIMESTAMP.getName());
			String keyPeerId = Attack.generatorFieldKey(id, Entity.Attack.PEERID.getName());
			String keyPeerName = Attack.generatorFieldKey(id, Entity.Attack.PEERNAME.getName());
			
			RString.set(keyUserRaceId, String.valueOf(attack.getUserRaceId()));
			RString.set(keyPercentage, String.valueOf(attack.getPercentage()));
			RString.set(keyStar, String.valueOf(attack.getStar()));
			RString.set(keyUseDonatedArmy, String.valueOf(attack.getUseDonatedArmy() == true ? 1 : 0));
			RString.set(keyRewardMedal, String.valueOf(attack.getRewardMedal()));
			RString.set(keyRewardCrown, String.valueOf(attack.getRewardCrown()));
			RString.set(keyRewardGoldByCrownLevel, String.valueOf(attack.getRewardGoldByCrownLevel()));
			RString.set(keyRewardOilByCrownLevel, String.valueOf(attack.getRewardOilByCrownLevel()));
			RString.set(keyTimestamp, String.valueOf(attack.getTimestamp()));
			RString.set(keyPeerId, String.valueOf(attack.getPeerId()));
			RString.set(keyPeerName, attack.getPeerName());

			addSetCollection(attack.getUserRaceId(), String.valueOf(id));
			
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		
		return id;
	}
	
	public Attack getAttack(Long id) throws DBException {
		Attack attack = new Attack();
		try {
			String keyUserRaceId = Attack.generatorFieldKey(id, Entity.Attack.USERRACEID.getName());
			String keyPercentage = Attack.generatorFieldKey(id, Entity.Attack.PERCENTAGE.getName());
			String keyStar = Attack.generatorFieldKey(id, Entity.Attack.STAR.getName());
			String keyUseDonatedArmy = Attack.generatorFieldKey(id, Entity.Attack.USEDONATEDARMY.getName());
			String keyRewardMedal = Attack.generatorFieldKey(id, Entity.Attack.REWARDMEDAL.getName());
			String keyRewardCrown = Attack.generatorFieldKey(id, Entity.Attack.REWARDCROWN.getName());
			String keyRewardGoldByCrownLevel = Attack.generatorFieldKey(id, Entity.Attack.REWARDGOLDBYCROWNLEVEL.getName());
			String keyRewardOilByCrownLevel = Attack.generatorFieldKey(id, Entity.Attack.REWARDOILBYCROWNLEVEL.getName());
			String keyTimestamp = Attack.generatorFieldKey(id, Entity.Attack.TIMESTAMP.getName());
			String keyPeerId = Attack.generatorFieldKey(id, Entity.Attack.PEERID.getName());
			String keyPeerName = Attack.generatorFieldKey(id, Entity.Attack.PEERNAME.getName());

			attack.setId(id);
			attack.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
			attack.setPercentage(Integer.parseInt(RString.get(keyPercentage)));
			attack.setStar(Integer.parseInt(RString.get(keyStar)));
			String useDonatedArmy = RString.get(keyUseDonatedArmy);
			if(StringUtils.isNotEmpty(useDonatedArmy)) {
				attack.setUseDonatedArmy("1".equals(useDonatedArmy) ? true : false);
			}
			attack.setRewardMedal(Integer.parseInt(RString.get(keyRewardMedal)));
			attack.setRewardCrown(Integer.parseInt(RString.get(keyRewardCrown)));
			attack.setRewardGoldByCrownLevel(Integer.parseInt(RString.get(keyRewardGoldByCrownLevel)));
			attack.setRewardOilByCrownLevel(Integer.parseInt(RString.get(keyRewardOilByCrownLevel)));
			attack.setTimestamp(Long.parseLong(RString.get(keyTimestamp)));
			attack.setPeerId(Long.parseLong(RString.get(keyPeerId)));
			attack.setPeerName(RString.get(keyPeerName));

		} catch(RedisException e) {
			throw new DBException(e.getMessage());
		}
		
		return attack;
	}
	
	public void addSetCollection(Long userRaceId, String value) throws DBException {
		super.addSetCollection(Entity.ATTACK, String.valueOf(userRaceId), value);
	}
	
	public Set<String> getSetCollection(Long userRaceId) throws DBException {
		return super.getSetCollection(Entity.ATTACK, String.valueOf(userRaceId));
	}

}
