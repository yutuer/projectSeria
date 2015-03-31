package com.pureland.common.component.cache.service;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by qinpeirong on 15-1-29.
 */
public class RedisThreadLocal {
    public static Map<Long, Jedis> jedisMap = Maps.newHashMap();
}
