package com.pureland.common.component.cache.service;

import java.util.List;
import java.util.Map;

import com.pureland.common.component.cache.error.RedisException;

/**
 * @author qinpeirong
 */
public interface RStringService {
    public void set(byte[] key, byte[] value) throws RedisException;

    public void set(String key, String value) throws RedisException;

    public void set(String key, String value, long timeOutMills) throws RedisException;

    public String get(String key) throws RedisException;

    public byte[] get(byte[] key) throws RedisException;

    public List<String> mget(String... keys) throws RedisException;

    public void mset(String... keys) throws RedisException;

    public void del(String key) throws RedisException;

    public void del(String... keys) throws RedisException;

    //public Set<String> keys(String key)throws RedisException;

//    public Map<String,String> mgetKeyValue(String... keys) throws RedisException;

    public boolean exists(String key) throws RedisException;

    public Long generatorKey(String key) throws RedisException;
}
