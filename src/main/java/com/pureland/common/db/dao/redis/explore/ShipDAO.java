package com.pureland.common.db.dao.redis.explore;

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

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.error.DBException;
import com.pureland.common.util.SpringContextUtil;

/**
 * Created by Administrator on 2015/3/7.
 */
public class ShipDAO extends RedisDAO {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");

	/**
	 * key = USERRACE:1:SHIPID, value = sid shipid = incr(SHIP) key =
	 * SHIP:urid:sid value = ShipObject add UserRace:1:Ship sid
	 */
	public void addShip(final Ship ship) throws DBException {
		final Long userRaceId = ship.getUserRaceId();
		SessionCallback<Long> sessionCallback = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				String genIdKey = Ship.generatorUserRaceShipSidKey(userRaceId);
				ValueOperations<String, String> oper = operations.opsForValue();
				final Long sid = oper.increment(genIdKey, 1);
				ship.setSid(sid);
				return sid;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback);

		SessionCallback<Ship> sessionCallback_write = new SessionCallback<Ship>() {
			@Override
			public Ship execute(RedisOperations operations) throws DataAccessException {
				String keyId = Ship.getIdKeyString(userRaceId, ship.getSid());
				ValueOperations<String, Ship> oper = operations.opsForValue();
				oper.set(keyId, ship);
				return ship;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback_write);

		SessionCallback sessionCallback2 = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				String key = Ship.generatorShipSetKey(userRaceId);
				SetOperations<String, String> setOperations = operations.opsForSet();
				setOperations.add(key, String.valueOf(ship.getSid()));
				return null;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback2);
	}

	public Ship getShip(final Long userRaceId, final Long shipSid) throws DBException {
		SessionCallback<Ship> sessionCallback_read = new SessionCallback<Ship>() {
			@Override
			public Ship execute(RedisOperations operations) throws DataAccessException {
				String keyId = Ship.getIdKeyString(userRaceId, shipSid);
				ValueOperations<String, Ship> oper = operations.opsForValue();
				Ship ship = oper.get(keyId);
				return ship;
			}
		};
		return (Ship) valueJdkRedisTemplate.execute(sessionCallback_read);
	}

	public List<Ship> getShips(final Long userRaceId, final List<String> shipSids) throws DBException {
		RedisCallback<Ship> pipelineCallback = new RedisCallback<Ship>() {
			@Override
			public Ship doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				for (String shipSid : shipSids) {
					String keyId = Ship.getIdKeyString(userRaceId, Long.valueOf(shipSid));
					stringRedisConn.get(keyId);
				}
				return null;
			}
		};
		List<Ship> results = Lists.newArrayList(valueJdkRedisTemplate.executePipelined(pipelineCallback));
		return results;
	}

	public void updateShip(final Ship ship) throws DBException {
		final Long userRaceId = ship.getUserRaceId();
		final Long shipSid = ship.getSid();
		SessionCallback<Ship> sessionCallback_write = new SessionCallback<Ship>() {
			@Override
			public Ship execute(RedisOperations operations) throws DataAccessException {
				String keyId = Ship.getIdKeyString(userRaceId, shipSid);
				ValueOperations<String, Ship> oper = operations.opsForValue();
				oper.set(keyId, ship);
				return ship;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback_write);
	}

	public Long getShipsCount(Long userRaceId) throws DBException {
		SessionCallback<Long> sessionCallback2 = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				String key = Ship.generatorShipSetKey(userRaceId);
				SetOperations<String, String> setOperations = operations.opsForSet();
				return setOperations.size(key);
			}
		};
		return (Long) valueStrRedisTemplate.execute(sessionCallback2);
	}

	public List<String> getShipIds(Long userRaceId) {
		SessionCallback<Set<String>> sessionCallback2 = new SessionCallback<Set<String>>() {
			@Override
			public Set<String> execute(RedisOperations operations) throws DataAccessException {
				String key = Ship.generatorShipSetKey(userRaceId);
				SetOperations<String, String> setOperations = operations.opsForSet();
				return setOperations.members(key);
			}
		};
		return Lists.newArrayList((Set<String>) (valueStrRedisTemplate.execute(sessionCallback2)));
	}
}
