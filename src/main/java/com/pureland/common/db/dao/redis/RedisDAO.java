package com.pureland.common.db.dao.redis;

import com.google.common.collect.Lists;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @author qinpeirong
 */
@Repository
public abstract class RedisDAO {

    @Autowired
    protected JedisPool jedisPoolMaster;

    @Autowired
    protected JedisPool jedisPoolSlave;

    private String TAG = PurelandLog.getClassTag(RedisDAO.class);

    protected Jedis createMasterClient() {
        Jedis resource = jedisPoolMaster.getResource();
        return resource;
    }

    protected Jedis createSlaveClient() {
        Jedis resource = jedisPoolSlave.getResource();
        return resource;
    }

    protected void addListCollection(Entity entity, String value, String... field) throws DBException {
        Jedis masterClient = null;
        try {
            String key = entity.getName() + "LIST";
            String fieldStr = StringUtils.join(field, Entity.SEPARATOR);
            key = StringUtils.join(new String[]{key, fieldStr}, Entity.SEPARATOR);
            masterClient = createMasterClient();
            masterClient.rpush(key, value);
        } finally {
            if (masterClient != null) {
                jedisPoolMaster.returnResource(masterClient);
            }
        }
    }

    protected List<String> getListCollection(Entity entity, String... field) throws DBException {
        Jedis slaveClient = null;
        List<String> ret = Lists.newArrayList();
        try {
            String key = entity.getName() + "LIST";
            String fieldStr = StringUtils.join(field, Entity.SEPARATOR);
            key = StringUtils.join(new String[]{key, fieldStr}, Entity.SEPARATOR);
            slaveClient = createSlaveClient();
            ret = slaveClient.lrange(key, 0L, -1L);
        } finally {
            if (slaveClient != null) {
                jedisPoolSlave.returnResource(slaveClient);
            }
        }
        return ret;
    }

    protected void addSetCollection(Entity entity, String field, String value) throws DBException {
        Jedis masterClient = null;
        try {
            String key = entity.getName();
            if (StringUtils.isNotEmpty(field)) {
                key = key + Entity.SEPARATOR + field;
            }
            masterClient = createMasterClient();
            masterClient.sadd(key, value);
        } finally {
            if (masterClient != null) {
                jedisPoolMaster.returnResource(masterClient);
            }
        }
    }

    protected Set<String> getSetCollection(Entity entity, String field) throws DBException {
        Jedis slaveClient = null;
        Set<String> smembers;
        try {
            String key = entity.getName();
            if (StringUtils.isNotEmpty(field)) {
                key = key + Entity.SEPARATOR + field;
            }
            slaveClient = createSlaveClient();
            smembers = slaveClient.smembers(key);
        } finally {
            if (slaveClient != null) {
                jedisPoolSlave.returnResource(slaveClient);
            }
        }
        return smembers;
    }

    protected void delSetElement(Entity entity, String field, String value) throws DBException {
        Jedis masterClient = null;
        try {
            String key = entity.getName();
            if (StringUtils.isNotEmpty(field)) {
                key = key + Entity.SEPARATOR + field;
            }
            masterClient = createMasterClient();
            masterClient.srem(key, value);
        } finally {
            if (masterClient != null) {
                jedisPoolMaster.returnResource(masterClient);
            }
        }
    }

    protected Boolean sisSetMember(Entity entity, String filed, String value) throws DBException {
        Jedis slaveClient = null;
        Boolean sismember = false;
        try {
            String key = entity.getName();
            if (StringUtils.isNotEmpty(filed)) {
                key = key + Entity.SEPARATOR + filed;
            }
            slaveClient = createSlaveClient();
            sismember = slaveClient.sismember(key, value);
        } finally {
            if (slaveClient != null) {
                jedisPoolSlave.returnResource(slaveClient);
            }
        }
        return sismember;
    }

    protected void addSortedSetCollection(Entity entity, double score, String value, String... fields) throws DBException {
        Jedis masterClient = null;
        try {
            String key = entity.getName();
            for (String field : fields) {
                key = key + Entity.SEPARATOR + field;
            }
            masterClient = createMasterClient();
            masterClient.zadd(key, score, value);
        } finally {
            if (masterClient != null) {
                jedisPoolMaster.returnResource(masterClient);
            }
        }
    }

    protected Set<String> getSortedSetCollection(Entity entity, Integer start, Integer end, String... fields) throws DBException {
        Jedis slaveClient = null;
        Set<String> zrange;
        try {
            String key = entity.getName();
            for (String field : fields) {
                key = key + Entity.SEPARATOR + field;
            }

            start = start == null ? 0 : start;
            end = end == null ? -1 : end;
            slaveClient = createSlaveClient();
            zrange = slaveClient.zrange(key, start, end);
        } finally {
            if (slaveClient != null) {
                jedisPoolSlave.returnResource(slaveClient);
            }
        }
        return zrange;
    }

    protected Long deleteSortedSetElement(Entity entity, String member, String... fields) throws DBException {
        Jedis masterClient = null;
        Long zrem;
        try {
            String key = entity.getName();
            for (String field : fields) {
                key = key + Entity.SEPARATOR + field;
            }

            masterClient = createMasterClient();
            zrem = masterClient.zrem(key, member);
        } finally {
            if (masterClient != null) {
                jedisPoolMaster.returnResource(masterClient);
            }
        }
        return zrem;
    }


}
