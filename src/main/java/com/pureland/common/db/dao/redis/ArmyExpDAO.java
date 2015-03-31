package com.pureland.common.db.dao.redis;

import java.util.Set;

import com.google.common.collect.Sets;
import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;

/**
 * Created by Administrator on 2015/2/4.
 */
public class ArmyExpDAO extends RedisDAO {

	public Long getArmyExpId(Long userRaceId, Integer cid) throws DBException {
		try {
			String keyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			String idString = RString.get(keyId);
			if (idString == null) {
				throw new DBException("查询armyExp错误,userRaceId:%d,cid:%d", userRaceId, cid);
			}
			return Long.valueOf(idString);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Long addArmyExp(ArmyExp armyExp) throws DBException {
		Long id = null;
		Long userRaceId = armyExp.getUserRaceId();
		Integer cid = armyExp.getCid();
		Integer exp = armyExp.getExp();
		try {
			id = ArmyExp.generatorArmyExpId();
			String keyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			String keyUserRaceId = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.USERRACEID.getName());
			String keyCid = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.CID.getName());
			String keyExp = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.EXP.getName());

			RString.set(keyId, String.valueOf(id));
			RString.set(keyUserRaceId, String.valueOf(userRaceId));
			RString.set(keyCid, String.valueOf(cid));
			RString.set(keyExp, String.valueOf(exp));

			addSetCollection(Entity.ARMYEXP, String.valueOf(userRaceId), String.valueOf(id));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return id;
	}

	public ArmyExp getArmyExp(Long userRaceId, Integer cid) throws DBException {
		ArmyExp armyExp = null;
		try {
			String keyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			String idString = RString.get(keyId);
			if (idString != null) {
				Long id = Long.valueOf(idString);
				String keyExp = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.EXP.getName());
				Integer exp = null;
				String expString = RString.get(keyExp);
				if (expString != null) {
					exp = Integer.parseInt(expString);
				}
				armyExp = new ArmyExp();
				armyExp.setCid(cid);
				armyExp.setUserRaceId(userRaceId);
				armyExp.setId(id);
				armyExp.setExp(exp);
			}
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return armyExp;
	}

	public void deleteArmyExp(Long userRaceId, Integer cid) throws DBException {
		Long id = null;
		try {
			String keyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			String keyUserRaceId = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.USERRACEID.getName());
			String keyCid = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.CID.getName());
			String keyExp = ArmyExp.generatorFieldKey(id, Entity.ArmyExp.EXP.getName());

			String idValue = RString.get(keyId);

			RString.del(keyId);
			RString.del(keyUserRaceId);
			RString.del(keyCid);
			RString.del(keyExp);

			delSetElement(Entity.ARMYEXP, String.valueOf(userRaceId), idValue);
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public ArmyExp getArmyExp(Long armyExpId) throws DBException {
		ArmyExp armyExp = null;
		try {
			String keyUserRaceId = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.USERRACEID.getName());
			String keyCid = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.CID.getName());
			String keyExp = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.EXP.getName());

			armyExp = new ArmyExp();
			armyExp.setId(armyExpId);

			Integer index = 0;
			String userRaceId = RString.get(keyUserRaceId);
			if (userRaceId != null) {
				armyExp.setUserRaceId(Long.parseLong(userRaceId));
			}
			String cid = RString.get(keyCid);
			if (cid != null) {
				armyExp.setCid(Integer.parseInt(cid));
			}
			String exp = RString.get(keyExp);
			if (exp != null) {
				armyExp.setExp(Integer.parseInt(exp));
			}
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		return armyExp;
	}

	public Set<ArmyExp> getArmyExps(Long userRaceId) throws DBException {
		Set<String> ids = getSetCollection(Entity.ARMYEXP, String.valueOf(userRaceId));
		Set<ArmyExp> set = Sets.newHashSet();
		for (String id : ids) {
			ArmyExp armyExp = getArmyExp(Long.valueOf(id));
			set.add(armyExp);
		}
		return set;
	}

	public Set<String> getArmyExpCids(Long userRaceId) throws DBException {
		Set<String> ids = getSetCollection(Entity.ARMYEXP, String.valueOf(userRaceId));
		Set<String> set = Sets.newHashSet();
		for (String id : ids) {
			ArmyExp armyExp = getArmyExp(Long.valueOf(id));
			set.add(String.valueOf(armyExp.getCid()));
		}
		return set;
	}

	public int addArmyExpExp(Long armyExpId, Integer exp) throws DBException {
		try {
			ArmyExp armyExp = getArmyExp(armyExpId);
			int totalExp = armyExp.getExp() + exp;
			String keyExp = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.EXP.getName());
			RString.set(keyExp, String.valueOf(totalExp));
			return totalExp;
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void upgradeCid(Long userRaceId, Integer cid, Integer upgradeId) throws DBException {
		// ,
		try {
			// 更新主键查找方式,不更新id
			String beforeKeyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			String beforeValueId = RString.get(beforeKeyId);
			String afterKeyId = ArmyExp.generatorArmyExpKeyId(userRaceId, upgradeId);
			RString.set(afterKeyId, beforeValueId);
			// 更新cid
			Long armyExpId = Long.valueOf(beforeValueId);
			String keyCid = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.CID.getName());
			RString.set(keyCid, String.valueOf(upgradeId));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Long getArmyExpIdByCid(Long userRaceId, Integer cid) throws DBException {
		try {
			String keyId = ArmyExp.generatorArmyExpKeyId(userRaceId, cid);
			return Long.valueOf(RString.get(keyId));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}
}
