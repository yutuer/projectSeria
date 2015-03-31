package com.pureland.common.component.cache.api;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RSetService;
import com.pureland.common.component.cache.service.impl.RSetServiceImpl;
import com.pureland.common.util.SpringContextUtil;

/**
 * 
 * @author qinpeirong
 *
 */
public class RSet {

	private static RSetService rSetService;
	
	static {
		rSetService = (RSetService) SpringContextUtil.getBean(RSetServiceImpl.class.getSimpleName());
	}
	
	public static Long sadd(String key, String ... elments) throws RedisException {
        return rSetService.sadd(key, elments);
    }

    public static Boolean sismember(String key, String member) throws RedisException {
        return rSetService.sismember(key, member);
    }

    public static Set<String> smembers(String key) throws RedisException {
        return rSetService.smembers(key);
    }

    public static Long srem(String key, String ... elments) throws RedisException {
        return rSetService.srem(key, elments);
    }

    public static String spop(String key) throws RedisException {
        return rSetService.spop(key);
    }
    
    public static Long generator(String key) throws RedisException {
    	return rSetService.generator(key);
    }
}
