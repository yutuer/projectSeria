package com.pureland.common.component.cache.api;

import java.util.List;

import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.component.cache.service.RStringService;
import com.pureland.common.component.cache.service.impl.RStringServiceImpl;
import com.pureland.common.util.SpringContextUtil;

/**
 * @author qinpeirong
 */
public class RString {

    private static final RStringService rStringService;

    static {
        rStringService = (RStringService) SpringContextUtil.getBean(RStringServiceImpl.class.getSimpleName());
    }

    public static void set(byte[] key, byte[] value) throws RedisException {
        rStringService.set(key, value);
    }

    public static void set(String key, String value) throws RedisException {
        rStringService.set(key, value);
    }

    public static void set(String key, String value, long timeOutMill) throws RedisException {
        rStringService.set(key, value, timeOutMill);
    }

    public static String get(String key) throws RedisException {
        return rStringService.get(key);
    }

    public static byte[] get(byte[] key) throws RedisException {
        return rStringService.get(key);
    }

    public static void del(String key) throws RedisException {
        rStringService.del(key);
    }

    public static void del(String... keys) throws RedisException {
        rStringService.del(keys);
    }

    public static void mset(String... keys) throws RedisException {
        rStringService.mset(keys);
    }

    public static List<String> mget(String... keys) throws RedisException {
        return rStringService.mget(keys);
    }

    public static boolean exists(String key) throws RedisException {
        return rStringService.exists(key);
    }

    public static Long generator(String key) throws RedisException {
        return rStringService.generatorKey(key);
    }

}
