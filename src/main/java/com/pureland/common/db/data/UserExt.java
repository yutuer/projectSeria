package com.pureland.common.db.data;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pureland.common.enums.Entity;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.util.DataObject;

public class UserExt extends DataObject {

	private static final long serialVersionUID = 140409511447417149L;

	private Long id;
	private Long userRaceId;
	private Integer level;
	private Integer experience;
	private Integer crown;

	private List<Resource> resources;
	private Map<ResourceServerTypeEnum, Resource> resourcesForMap;

	public static String generatorIdKey(String singleMark) {
		return generatorIdKey(Entity.USEREXT, Entity.UserExt.ID.getName(), singleMark);
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.USEREXT, id, field);
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
	 * @return the userRaceId
	 */
	public Long getUserRaceId() {
		return userRaceId;
	}

	/**
	 * @param userRaceId
	 *            the userRaceId to set
	 */
	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * @param experience
	 *            the experience to set
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
		this.translateForMap(this.resources);
	}

	/**
	 * @return the resourcesForMap
	 */
	public Map<ResourceServerTypeEnum, Resource> getResourcesForMap() {
		return resourcesForMap;
	}

	/**
	 * @param resourcesForMap
	 *            the resourcesForMap to set
	 */
	public void setResourcesForMap(Map<ResourceServerTypeEnum, Resource> resourcesForMap) {
		this.resourcesForMap = resourcesForMap;
	}

	public Integer getCrown() {
		return crown;
	}

	public void setCrown(Integer crown) {
		this.crown = crown;
	}

	private void translateForMap(List<Resource> resouces) {
		Map<ResourceServerTypeEnum, Resource> resourcesForMap = Maps.newHashMap();
		for (Resource resource : resouces) {
			resourcesForMap.put(resource.getResourceType(), resource);
		}
		this.setResourcesForMap(resourcesForMap);
	}

}
