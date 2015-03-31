package com.pureland.common.db.dao.redis.clan;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;

import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.error.DBException;
import com.pureland.common.util.SpringContextUtil;

public class DonateArmyDAO extends RedisDAO {
	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");

	public DonateArmy getUserRaceDonateArmy(Long userRaceId) throws DBException {
		SessionCallback<DonateArmy> sessionCallback = new SessionCallback<DonateArmy>() {
			@Override
			public DonateArmy execute(RedisOperations operations) throws DataAccessException {
				String keyId = Clan.getDonateArmyKeyIdString(userRaceId);
				ValueOperations<String, DonateArmy> valueOperations = operations.opsForValue();
				return valueOperations.get(keyId);
			}
		};
		return (DonateArmy) valueJdkRedisTemplate.execute(sessionCallback);
	}

	public void setUserRaceDonateArmy(final DonateArmy donateArmy) throws DBException {
		SessionCallback<DonateArmy> sessionCallback = new SessionCallback<DonateArmy>() {
			@Override
			public DonateArmy execute(RedisOperations operations) throws DataAccessException {
				String keyId = Clan.getDonateArmyKeyIdString(donateArmy.getUserRaceId());
				ValueOperations<String, DonateArmy> valueOperations = operations.opsForValue();
				valueOperations.set(keyId, donateArmy);
				return null;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);
	}

	public void resetUserRaceDonateArmy(final Long userRaceId) throws DBException {
		SessionCallback<DonateArmy> sessionCallback = new SessionCallback<DonateArmy>() {
			@Override
			public DonateArmy execute(RedisOperations operations) throws DataAccessException {
				String keyId = Clan.getDonateArmyKeyIdString(userRaceId);
				ValueOperations<String, DonateArmy> valueOperations = operations.opsForValue();
				DonateArmy donateArmy = valueOperations.get(keyId);
				if (donateArmy == null) {
					return null;
				}
				donateArmy.getDonateInfoMap().clear();
				valueOperations.set(keyId, donateArmy);
				return null;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);
	}

}
