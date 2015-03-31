package com.pureland.common.db.data;

import com.pureland.common.enums.Entity;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.util.DataObject;

public class Resource extends DataObject {

	private static final long serialVersionUID = 7304605458908821002L;

	private Long id;
	private Long userExtId;
	private ResourceServerTypeEnum resourceType;
	private Integer count;

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.RESOURCE, id, field);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userExtId
	 */
	public Long getUserExtId() {
		return userExtId;
	}

	/**
	 * @param userExtId
	 *            the userExtId to set
	 */
	public void setUserExtId(Long userExtId) {
		this.userExtId = userExtId;
	}

	/**
	 * @return the resourceType
	 */
	public ResourceServerTypeEnum getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType
	 *            the resourceType to set
	 */
	public void setResourceType(ResourceServerTypeEnum resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

}
