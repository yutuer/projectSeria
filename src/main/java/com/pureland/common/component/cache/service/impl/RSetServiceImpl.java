package com.pureland.common.component.cache.service.impl;

import java.util.Set;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RedisHelper;
import com.pureland.common.component.cache.service.RSetService;
import redis.clients.jedis.Jedis;

/**
 * 
 * @author qinpeirong
 *
 */
public class RSetServiceImpl extends RedisHelper implements RSetService {

	@Override
	public Long sadd(String key, String... elments) throws RedisException {
		Jedis client = null;
		Long sadd;
		try {
			client = createMasterClient();
			sadd = client.sadd(key, elments);
		} finally {
			if (client != null) {
				jedisPoolMaster.returnResource(client);
			}
		}
		return sadd;
	}

	@Override
	public Boolean sismember(String key, String member) throws RedisException {
		Jedis client = null;
		Boolean sismember;
		try {
			client = createSlaveClient();
			sismember = client.sismember(key, member);
		} finally {
            jedisPoolSlave.returnResource(client);
		}
		return sismember;
	}

	@Override
	public Set<String> smembers(String key) throws RedisException {
		Jedis client = null;
		Set<String> smembers;
		try {
			client = createSlaveClient();
			smembers = client.smembers(key);
		} finally {
			if (client != null) {
				jedisPoolSlave.returnResource(client);
			}
		}
		return smembers;
	}

	@Override
	public Long srem(String key, String... elments) throws RedisException {
		Jedis client = null;
		Long srem;
		try {
			client = createMasterClient();
			srem = client.srem(key, elments);
		} finally {
			if (client != null) {
				jedisPoolMaster.returnResource(client);
			}
		}
		return srem;
	}

	@Override
	public String spop(String key) throws RedisException {
		Jedis client = null;
		String spop;
		try {
			client = createSlaveClient();
			spop = client.spop(key);
		} finally {
			if (client != null) {
				jedisPoolSlave.returnResource(client);
			}
		}
		return spop;
	}

	@Override
	public Long generator(String key) throws RedisException {
		Jedis client = null;
		Long incr;
		try {
			client = createMasterClient();
			incr = client.incr(key);
		} finally {
			if (client != null) {
				jedisPoolMaster.returnResource(client);
			}
		}
		return incr;
	}

}
