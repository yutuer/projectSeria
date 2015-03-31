package com.pureland.common.db.dao.redis;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.ArmyExp;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class ArmyDAO extends RedisDAO {

	private String TAG = PurelandLog.getClassTag(ArmyDAO.class);

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");
	private ArmyExpDAO armyExpDAO = (ArmyExpDAO) SpringContextUtil.getBean(ArmyExpDAO.class.getSimpleName());

	public String addArmy(final Army army) throws DBException {
		SessionCallback<String> sessionCallback = new SessionCallback<String>() {
			@Override
			public String execute(RedisOperations operations) throws DataAccessException {
				Long userRaceId = army.getUserRaceId();
				Long armyExpId = army.getArmyExpId();
				Integer amount = army.getAmount();

				ValueOperations valueOperations = operations.opsForValue();
				Long id = valueOperations.increment(Entity.ARMY.getName(), 1);

				String keyCid = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.CID.getName());
				String cid = (String) valueOperations.get(keyCid);

				String keyId = Army.generatorIdKey(new String[] { String.valueOf(userRaceId), cid });
				String keyUserRaceId = Army.generatorFieldKey(id, Army.Field.USERRACEID.name());
				String keyArmyExpId = Army.generatorFieldKey(id, Army.Field.ARMYEXPID.name());
				String keyAmount = Army.generatorFieldKey(id, Army.Field.AMOUNT.name());

				operations.multi();
				valueOperations.set(keyId, String.valueOf(id));
				valueOperations.set(keyUserRaceId, String.valueOf(userRaceId));
				valueOperations.set(keyArmyExpId, String.valueOf(armyExpId));
				valueOperations.set(keyAmount, String.valueOf(amount));

				SetOperations setOperations = operations.opsForSet();
				String keyArmy = Army.getArmyIdKeyString(userRaceId);
				setOperations.add(keyArmy, String.valueOf(id));
				operations.exec();
				return String.valueOf(id);
			}
		};
		String ret = (String) valueStrRedisTemplate.execute(sessionCallback);
		addSetCollection(army.getUserRaceId(), ret);
		return ret;
	}

	public Army getArmyByCid(final Long userRaceId, final Integer cid) throws DBException {
		SessionCallback<String> sessionCallback = new SessionCallback<String>() {
			@Override
			public String execute(RedisOperations operations) throws DataAccessException {
				String keyArmyId = Army.generatorIdKey(new String[] { userRaceId.toString(), cid.toString() });
				ValueOperations<String, String> valueOperations = operations.opsForValue();
				return valueOperations.get(keyArmyId);
			}
		};
		String aid = (String) valueStrRedisTemplate.execute(sessionCallback);
		if (aid == null) {
			return null;
		}
		final String aId = (String) valueStrRedisTemplate.execute(sessionCallback);
		final Long armyId = Long.valueOf(aId);
		RedisCallback<String> redisCallback = new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				String keyUserRaceId = Army.generatorFieldKey(armyId, Army.Field.USERRACEID.name());
				String keyCid = Army.generatorFieldKey(armyId, Army.Field.ARMYEXPID.name());
				String keyAmount = Army.generatorFieldKey(armyId, Army.Field.AMOUNT.name());

				StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
				stringRedisConnection.get(keyUserRaceId);
				stringRedisConnection.get(keyCid);
				stringRedisConnection.get(keyAmount);
				return null;
			}
		};
		List<Object> list = valueStrRedisTemplate.executePipelined(redisCallback);
		int index = 0;
		Army army = new Army();
		army.setId(armyId);
		army.setUserRaceId(Long.parseLong(list.get(index++).toString()));
		army.setArmyExpId(Long.parseLong(list.get(index++).toString()));
		army.setAmount(Integer.parseInt(list.get(index++).toString()));
		return army;
	}

	public Army getArmy(final Long armyId) throws DBException {
		RedisCallback<String> redisCallback = new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				String keyUserRaceId = Army.generatorFieldKey(armyId, Army.Field.USERRACEID.name());
				String keyCid = Army.generatorFieldKey(armyId, Army.Field.ARMYEXPID.name());
				String keyAmount = Army.generatorFieldKey(armyId, Army.Field.AMOUNT.name());

				StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
				stringRedisConnection.get(keyUserRaceId);
				stringRedisConnection.get(keyCid);
				stringRedisConnection.get(keyAmount);
				return null;
			}
		};
		List<Object> list = valueStrRedisTemplate.executePipelined(redisCallback);
		int index = 0;
		Army army = new Army();
		army.setId(armyId);
		army.setUserRaceId(Long.parseLong(list.get(index++).toString()));
		army.setArmyExpId(Long.parseLong(list.get(index++).toString()));
		army.setAmount(Integer.parseInt(list.get(index++).toString()));
		return army;
	}

	public void updateArmy(Army army) throws DBException {
		Long id = army.getId();
		Integer amount = army.getAmount();
		if (id == null) {
			throw new DBException("");
		}
		try {
			if (amount != null) {
				if (amount == 0) {
					deleteArmy(army);
				} else {
					String keyAmount = Army.generatorFieldKey(id, Entity.Army.AMOUNT.getName());
					RString.set(keyAmount, String.valueOf(amount));
				}
			}
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void deleteArmy(Army army) throws DBException {
		Long id = army.getId();
		Long userRaceId = army.getUserRaceId();
		Long armyExpId = army.getArmyExpId();
		if (id == null || userRaceId == null || armyExpId == null) {
			throw new DBException("id, userRaceId or armyExpId is null");
		}
		try {
			String keyCid = ArmyExp.generatorFieldKey(armyExpId, Entity.ArmyExp.CID.getName());
			String cid = RString.get(keyCid);

			String keyId = Army.generatorIdKey(new String[] { userRaceId.toString(), cid });
			String keyUserRaceId = Army.generatorFieldKey(id, Entity.Army.USERRACEID.getName());
			String keyArmyExpId = Army.generatorFieldKey(id, Entity.Army.ARMYEXPID.getName());
			String keyAmount = Army.generatorFieldKey(id, Entity.Army.AMOUNT.getName());

			String[] delKeys = { keyId, keyUserRaceId, keyArmyExpId, keyAmount };

			RString.del(delKeys);
			delSetElement(userRaceId, String.valueOf(id));
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public void addSetCollection(Long userRaceId, String value) throws DBException {
		super.addSetCollection(Entity.ARMY, String.valueOf(userRaceId), value);
	}

	public Set<String> getSetCollection(Long userRaceId) throws DBException {
		return super.getSetCollection(Entity.ARMY, String.valueOf(userRaceId));
	}

	public void delSetElement(Long userRaceId, String value) throws DBException {
		super.delSetElement(Entity.ARMY, String.valueOf(userRaceId), value);
	}
}
