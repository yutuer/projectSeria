package com.pureland.common.component.cache.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RListService;
import com.pureland.common.component.cache.service.impl.RListServiceImpl;
import com.pureland.common.util.SpringContextUtil;

/**
 * 
 * @author qinpeirong
 *
 */
public class RList {

	private static RListService rListService;
	
	static {
		rListService = (RListService) SpringContextUtil.getBean(RListServiceImpl.class.getSimpleName());
	}
	
	public static void lpush(String key, String value) throws RedisException {
        rListService.lpush(key, value);
    }

    public static void rpush(String key, String value) throws RedisException {
        rListService.rpush(key, value);
    }

    public static void linsert(String key, String value, String before) throws RedisException {
        rListService.linsert(key, value, before);
    }

    public static String lpop(String key) throws RedisException {
        return rListService.lpop(key);
    }

    public static Long llen(String key) throws RedisException {
        return rListService.llen(key);
    }

    public static List<String> lrange(String key, Long start, Long end) throws RedisException {
        return rListService.lrange(key, start, end);
    }

    public static String lindex(String key, Long index) throws RedisException {
        return rListService.lindex(key, index);
    }

    public static void lrem(String key, Long index, String value) throws RedisException {
        rListService.lrem(key, index, value);
    }

    public static void ltrim(String key, Long start, Long end) throws RedisException {
        rListService.ltrim(key, start, end);
    }
    
    public static Long generator(String key) throws RedisException {
    	return rListService.generator(key);
    }
}
