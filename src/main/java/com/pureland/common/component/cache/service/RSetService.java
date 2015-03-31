package com.pureland.common.component.cache.service;

import java.util.Set;

import com.pureland.common.component.cache.error.RedisException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface RSetService {

	public Long sadd(String key, String ... elments) throws RedisException;

    public Boolean sismember(String key, String member) throws RedisException;

    public Set<String> smembers(String key) throws RedisException;

    public Long srem(String key, String ... elments) throws RedisException;

    public String spop(String key) throws RedisException;

    public Long generator(String key) throws RedisException;
}
