package com.pureland.common.component.cache.service.impl;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RSortedSetService;
import com.pureland.common.component.cache.service.RedisHelper;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * @author qinpeirong
 */
public class RSortedSetServiceImpl extends RedisHelper implements
        RSortedSetService {

    @Override
    public Long zadd(String key, double score, String member)
            throws RedisException {
        Jedis client = null;
        Long zadd;
        try {
            client = createMasterClient();
            zadd = client.zadd(key, score, member);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
        return zadd;
    }

    @Override
    public Long zadd(String key, Map<String, Double> scoreMembers)
            throws RedisException {
        Jedis client = null;
        Long zadd;
        try {
            client = createMasterClient();
            zadd = client.zadd(key, scoreMembers);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
        return zadd;
    }

    @Override
    public Set<String> zrange(String key, long start, long end)
            throws RedisException {
        Jedis client = null;
        Set<String> zrange;
        try {
            client = createSlaveClient();
            zrange = client.zrange(key, start, end);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return zrange;
    }

    @Override
    public Set<String> zrangebyScore(String key, Double min, Double max)
            throws RedisException {
        Jedis client = null;
        Set<String> zrangebyScore;
        try {
            client = createSlaveClient();
            zrangebyScore = client.zrangeByScore(key, min, max);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return zrangebyScore;
    }


    @Override
    public Set<String> zrevrange(String key, long start, long end)
            throws RedisException {
        Jedis client = null;
        Set<String> zrevrange;
        try {
            client = createSlaveClient();
            zrevrange = client.zrevrange(key, start, end);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return zrevrange;
    }

    @Override
    public Long zrem(String key, String... members) throws RedisException {
        Jedis client = null;
        Long zrem;
        try {
            client = createMasterClient();
            zrem = client.zrem(key, members);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
        return zrem;
    }

    @Override
    public Double zscore(String key, String member) throws RedisException {
        Jedis client = null;
        Double zscore;
        try {
            client = createMasterClient();
            zscore = client.zscore(key, member);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
        return zscore;
    }

}
