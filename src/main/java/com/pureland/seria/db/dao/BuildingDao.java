package com.pureland.seria.db.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.pureland.common.util.SpringContextUtil;
import com.pureland.seria.module.BuildingModule;
import com.pureland.seria.module.ModuleEnum;

public class BuildingDao {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");

	public BuildingModule getBuildingModule(Long playerId) {
		String key = BuildingModule.getKeyString(playerId);
		HashOperations<String, String, BuildingModule> oper = valueJdkRedisTemplate.opsForHash();
		return oper.get(key, ModuleEnum.Building.name());
	}

	public void updateBuildingModule(BuildingModule bm) {
		String key = BuildingModule.getKeyString(bm.getPlayerId());
		HashOperations<String, String, BuildingModule> oper = valueJdkRedisTemplate.opsForHash();
		oper.put(key, ModuleEnum.Building.name(), bm);
	}

}
