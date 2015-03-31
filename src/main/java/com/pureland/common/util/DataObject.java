package com.pureland.common.util;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pureland.common.enums.Entity;

public abstract class DataObject implements Serializable {

	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updateTime;

	private Entity entity;

	public static final String FIELD_CREATE_USER = "createUser";
	public static final String FIELD_CREATE_TIME = "createTime";
	public static final String FIELD_UPDATE_USER = "updateUser";
	public static final String FIELD_UPDATE_TIME = "updateTime";

	public static void setProperty(String property, ValueOperations<String, String> oper, String keyId, Object value) {
		if (value == null) {
			return;
		}
		String key = StringUtils.join(keyId, Entity.SEPARATOR, property);
		oper.set(key, String.valueOf(value));
	}

	/**
	 * 生成redis中id主键key
	 *
	 * @param entity
	 * @param field
	 * @param singleMark
	 * @return
	 */
	public static String generatorIdKey(Entity entity, String field, String singleMark) {
		StringBuffer idKey = new StringBuffer();
		idKey.append(entity.getName()).append(Entity.SEPARATOR).append(singleMark);
		if (field != null) {
			idKey.append(Entity.SEPARATOR).append(field);
		}
		return idKey.toString();
	}

	public static String generatorFieldKey(Entity entity, Long id, String field) {
		StringBuffer key = new StringBuffer();
		key.append(entity.getName()).append(Entity.SEPARATOR).append(id);
		if (StringUtils.isNotEmpty(field))
			key.append(Entity.SEPARATOR).append(field);

		return key.toString();
	}

	public static String generatorFieldKey(Entity entity, String id, String field) {
		StringBuffer key = new StringBuffer();
		key.append(entity.getName()).append(Entity.SEPARATOR).append(id);
		if (StringUtils.isNotEmpty(field))
			key.append(Entity.SEPARATOR).append(field);

		return key.toString();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public <T extends DBObject> T toDBObject() {
		BasicDBObject dbObj = new BasicDBObject();
		if (!StringUtils.isEmpty(createUser)) {
			dbObj.put(FIELD_CREATE_USER, createUser);
		}
		if (createTime != null) {
			dbObj.put(FIELD_CREATE_TIME, createTime);
		}
		if (!StringUtils.isEmpty(updateUser)) {
			dbObj.put(FIELD_UPDATE_USER, updateUser);
		}
		if (updateTime != null) {
			dbObj.put(FIELD_UPDATE_TIME, updateTime);
		}

		return (T) dbObj;

	}

	public DBObject toJSONObject() {
		return toDBObject();
	}

	@Override
	public String toString() {
		return toDBObject().toString();
	}
}
