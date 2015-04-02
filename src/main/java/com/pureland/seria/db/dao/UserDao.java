package com.pureland.seria.db.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.pureland.common.util.SpringContextUtil;
import com.pureland.seria.db.seriaData.User;
import com.pureland.seria.service.bean.MachineBean;

public class UserDao {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");

	public User getUser(String machineId) {
		String key = MachineBean.generatorIdKey(machineId);
		ValueOperations<String, User> oper = valueJdkRedisTemplate.opsForValue();
		return oper.get(key);
	}

	public void updateBuildingModule(String machineId, User user) {
		String key = MachineBean.generatorIdKey(machineId);
		ValueOperations<String, User> oper = valueJdkRedisTemplate.opsForValue();
		oper.set(key, user);
	}

}
