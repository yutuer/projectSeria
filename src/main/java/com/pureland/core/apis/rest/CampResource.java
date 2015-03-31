package com.pureland.core.apis.rest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.enums.Entity;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.common.collect.Maps;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.util.AbstractResource;
import com.pureland.common.util.SpringContextUtil;

/*
 * Created by qinpeirong on 14-10-31
 */
@Controller
@RequestMapping("/apis/camp")
public class CampResource extends AbstractResource {
    private String TAG = PurelandLog.getClassTag(CampResource.class);	
    
    private JedisPool jedisPool = (JedisPool) SpringContextUtil.getBean("jedisPoolMaster");
    
	/**
	 * @param callback
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/mainCamp", method = RequestMethod.POST)
	@ResponseBody
    public String mainCamp(
            @RequestParam(value = "cb", required = false) String callback,
            HttpServletRequest request, HttpServletResponse response){
		try {
			String s = readContent(request);
			System.out.println(s);

			String key1 = Entity.BUILDING.getName() + "1";
			System.out.println(RString.generator(key1));

			String key2 = Entity.BUILDING.getName() + "2";
			System.out.println(RString.generator(key2));

			String key3 = Entity.BUILDING.getName();
			System.out.println(RString.generator(key3));

		} catch (RedisException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Jedis resource = jedisPool.getResource();
//		PurelandLog.info(resource.info());
		return resource.info();
		
	}


	public String readContent(HttpServletRequest request) throws Exception {
		try {
			InputStream is = new BufferedInputStream(request.getInputStream());
			String contentEncoding = request.getHeader("Content-Encoding");
			String contentLength   = request.getHeader("Content-Length");
			logger.debug("Request contentLength = " + contentLength);
			if(contentLength != null && contentLength.equals("0")) {
				return "";
			}
			if(contentEncoding != null && contentEncoding.indexOf("gzip") != -1) {
				is = new GZIPInputStream(is);
			}

			String json = IOUtils.toString(is, "utf-8");
			return json;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(
            @RequestParam(value = "cb", required = false) String callback,
            HttpServletRequest request, HttpServletResponse response){
		Jedis jedis = jedisPool.getResource();
//		PurelandLog.info(jedis.info());
		
		String key = "mostUsedLanguages";
		jedis.zadd(key,4d,"Java");//ZADD
//		
		//We could add more than one value in one calling
		Map<String, Double> scoreMembers = Maps.newHashMap();
		scoreMembers.put("Python", 2d);
		scoreMembers.put("Javascript", 3d);
		jedis.zadd(key, scoreMembers);
//		 
//		//We could get the score for a member
//		PurelandLog.info("Number of Java users:" + jedis.zscore(key, "Java"));
//		
//		//We could get the number of elements on the set
//		PurelandLog.info("Number of elements:" + jedis.zcard(key));//ZCARD
//		
//		//get all the elements sorted from top to bottom
//		System.out.println(jedis.zrevrange(key, 0, -1));
		
//		jedis.zincrby(key, 20, "Python");
//		PurelandLog.info("Score after zincrby:" + jedis.zscore(key, "Python"));
//		System.out.println(jedis.zrevrange(key, 0, -1));
		
//		Set<String> zrange = jedis.zrevrange(key, 0, 0);
//		Iterator<String> iterator = zrange.iterator();
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		Long zremrangeByRank = jedis.zremrangeByRank(key, 0, 0);
//		System.out.println(zremrangeByRank);
		
		System.out.println(jedis.zrange(key, 0, -1));
//		Long zrem = jedis.zrem(key, "Python");
//		System.out.println(zrem);
//		System.out.println(jedis.zrange(key, 0, -1));
		
		Set<String> zrange = jedis.zrange(key, 0, 0);
		Iterator<String> iterator = zrange.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
}
