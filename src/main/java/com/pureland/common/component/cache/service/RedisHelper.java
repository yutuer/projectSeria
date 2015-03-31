package com.pureland.common.component.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author qinpeirong
 *
 */
@Service
public abstract class RedisHelper {

	@Autowired
	protected JedisPool jedisPoolMaster;

	@Autowired
	protected JedisPool jedisPoolSlave;
		  
//	protected Jedis createClient() {
////		Jedis redis = new Jedis ("127.0.0.1",6379);
//
////		JedisPoolConfig config = new JedisPoolConfig();
////	   final RedisManager rm = RedisManager.getInstance("127.0.0.1:6379");
////	   rm.enableReadWriteSeparation(true);
//
//		Jedis resource = jedisPool.getResource();
//		return resource;
//
//
////	   Jedis resource = jedisPool.getResource();
////	   return resource;
//	}

	protected Jedis createMasterClient() {
		Jedis resource = jedisPoolMaster.getResource();
		return resource;
	}

	protected Jedis createSlaveClient() {
		Jedis resource = jedisPoolSlave.getResource();
		return resource;
	}

}
