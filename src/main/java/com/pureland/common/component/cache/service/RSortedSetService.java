package com.pureland.common.component.cache.service;

import java.util.Map;
import java.util.Set;

import com.pureland.common.component.cache.error.RedisException;

/**
 * @author qinpeirong
 */
public interface RSortedSetService {

    public Long zadd(final String key, final double score, final String member) throws RedisException;

    public Long zadd(final String key, final Map<String, Double> scoreMembers) throws RedisException;

    public Set<String> zrange(final String key, final long start, final long end) throws RedisException;

    public Set<String> zrangebyScore(final String key, final Double min, final Double max) throws RedisException;

    public Set<String> zrevrange(final String key, final long start, final long end) throws RedisException;

    public Long zrem(final String key, final String... members) throws RedisException;

    public Double zscore(String key, String member) throws RedisException;
}
