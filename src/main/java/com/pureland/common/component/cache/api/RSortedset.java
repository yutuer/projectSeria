package com.pureland.common.component.cache.api;

import java.util.Map;
import java.util.Set;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RSortedSetService;
import com.pureland.common.component.cache.service.impl.RSortedSetServiceImpl;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class RSortedset {

    private static RSortedSetService rSortedSetService;

    static {
        rSortedSetService = (RSortedSetService) SpringContextUtil.getBean(RSortedSetServiceImpl.class.getSimpleName());
    }

    public static Long zadd(final String key, final double score, final String member) throws RedisException {
        return rSortedSetService.zadd(key, score, member);
    }

    public static Long zadd(final String key, final Map<String, Double> scoreMembers) throws RedisException {
        return rSortedSetService.zadd(key, scoreMembers);
    }

    public static Set<String> zrange(final String key, final long start, final long end) throws RedisException {
        return rSortedSetService.zrange(key, start, end);
    }

    public static Set<String> zrangebyScore(final String key, final Double min, final Double max) throws RedisException {
        return rSortedSetService.zrangebyScore(key, min, max);
    }

    public static Set<String> zrevrange(final String key, final long start, final long end) throws RedisException {
        return rSortedSetService.zrevrange(key, start, end);
    }

    public static Long zrem(final String key, final String... members) throws RedisException {
        return rSortedSetService.zrem(key, members);
    }

    public static Double zscore(final String key, final String member) throws RedisException {
        return rSortedSetService.zscore(key, member);
    }

}
