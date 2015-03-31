package com.pureland.common.component.cache.service;

import java.util.List;

import com.pureland.common.component.cache.error.RedisException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface RListService {

	public void lpush(String key, String value) throws RedisException;

	public void rpush(String key, String value) throws RedisException;

	public void linsert(String key, String value, String before) throws RedisException;

    public String lpop(String key) throws RedisException;

    public Long llen(String key) throws RedisException;

    public List<String> lrange(String key, Long start, Long end) throws RedisException;

    public String lindex(String key, Long index) throws RedisException;

    public void lrem(String key, Long index, String value) throws RedisException;

    public void ltrim(String key, Long start, Long end) throws RedisException;
    
    public Long generator(String key) throws RedisException;
}
