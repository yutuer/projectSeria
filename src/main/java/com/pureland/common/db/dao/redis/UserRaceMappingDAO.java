package com.pureland.common.db.dao.redis;

import java.util.Set;

import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;

/**
 * 
 * @author qinpeirong
 *
 */
public class UserRaceMappingDAO extends RedisDAO {

	public void addSetCollection(Long userId, String value) throws DBException {
		super.addSetCollection(Entity.USERRACEMAPPING, String.valueOf(userId), value);
	}
	
	public Set<String> getSetCollection(Long userId) throws DBException {
		return super.getSetCollection(Entity.USERRACEMAPPING, String.valueOf(userId));
	}
}
