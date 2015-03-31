package com.pureland.common.component.cache.service.impl;

import java.util.List;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RedisHelper;
import com.pureland.common.component.cache.service.RListService;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * @author qinpeirong
 */
public class RListServiceImpl extends RedisHelper implements RListService {

    @Override
    public void lpush(String key, String value) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.lpush(key, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void rpush(String key, String value) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.rpush(key, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void linsert(String key, String value, String before) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.linsert(key, Client.LIST_POSITION.BEFORE, before, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public String lpop(String key) throws RedisException {
        Jedis client = null;
        String lpop;
        try {
            client = createMasterClient();
            lpop = client.lpop(key);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
        return lpop;
    }

    @Override
    public Long llen(String key) throws RedisException {
        Jedis client = null;
        Long llen;
        try {
            client = createSlaveClient();
            llen = client.llen(key);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return llen;
    }

    @Override
    public List<String> lrange(String key, Long start, Long end)
            throws RedisException {
        Jedis client = null;
        List<String> lrange;
        try {
            client = createSlaveClient();
            lrange = client.lrange(key, start, end);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return lrange;
    }

    @Override
    public String lindex(String key, Long index) throws RedisException {
        Jedis client = null;
        String lindex;
        try {
            client = createSlaveClient();
            lindex = client.lindex(key, index);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return lindex;
    }

    @Override
    public void lrem(String key, Long index, String value)
            throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.lrem(key, index, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void ltrim(String key, Long start, Long end) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.ltrim(key, start, end);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
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
