package com.pureland.common.component.cache.service.impl;

import java.util.List;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RedisHelper;
import com.pureland.common.component.cache.service.RStringService;
import redis.clients.jedis.Jedis;

/**
 * @author qinpeirong
 */
public class RStringServiceImpl extends RedisHelper implements RStringService {

    @Override
    public void set(byte[] key, byte[] value) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.set(key, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void set(String key, String value) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.set(key, value);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void set(String key, String value, long timeOutMills) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.set(key, value);
            client.pexpire(key, timeOutMills);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public String get(String key) throws RedisException {
        Jedis client = null;
        String value;
        try {
            client = createSlaveClient();
            value = client.get(key);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return value;
    }

    @Override
    public byte[] get(byte[] key) throws RedisException {
        Jedis client = null;
        byte[] bytes;
        try {
            client = createSlaveClient();
            bytes = client.get(key);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return bytes;
    }

    @Override
    public List<String> mget(String... keys) throws RedisException {
        Jedis client = null;
        List<String> resultList;
        try {
            client = createSlaveClient();
            resultList = client.mget(keys);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return resultList;
    }

    @Override
    public void mset(String... keys) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.mset(keys);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void del(String key) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.del(key);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public void del(String... keys) throws RedisException {
        Jedis client = null;
        try {
            client = createMasterClient();
            client.del(keys);
        } finally {
            if (client != null) {
                jedisPoolMaster.returnResource(client);
            }
        }
    }

    @Override
    public boolean exists(String key) throws RedisException {
        Jedis client = null;
        Boolean exists;
        try {
            client = createSlaveClient();
            exists = client.exists(key);
        } finally {
            if (client != null) {
                jedisPoolSlave.returnResource(client);
            }
        }
        return exists;
    }

    @Override
    public Long generatorKey(String key) throws RedisException {
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
