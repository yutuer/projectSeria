package com.pureland.common.db.data;

import com.pureland.common.enums.*;
import com.pureland.common.util.DataObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

public class Ship extends DataObject {

	private static final long serialVersionUID = 8245715817700411167L;

	private Long sid;
	private Integer cid;
	private Long userRaceId;
	private int state;
	private Integer section;
	private Long endTime;
	private Resource upgradeCostResource;

	private Map<String, Resource> resourcesForMap;

	public static enum Field {
		IncrSid, SID, CID, USERRACEID, STATE, SECTION, ENDTIME, RESOURCEIDS;
	}

	public static void setSid(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.SID.name(), oper, keyId, value);
	}

	public static void setUserRaceId(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.USERRACEID.name(), oper, keyId, value);
	}

	public static void setCid(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.CID.name(), oper, keyId, value);
	}

	public static void setState(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.STATE.name(), oper, keyId, value);
	}

	public static void setSection(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.SECTION.name(), oper, keyId, value);
	}

	public static void setEndtime(ValueOperations<String, String> oper, String keyId, Object value) {
		setProperty(Ship.Field.ENDTIME.name(), oper, keyId, value);
	}

	public static String getIdKeyString(Long userRaceId, Long shipSid) {
		return StringUtils.join(new String[] { Entity.SHIP.name(), Entity.SEPARATOR, String.valueOf(userRaceId), Entity.SEPARATOR, String.valueOf(shipSid) });
	}

	public static String generatorUserRaceShipSidKey(Long userRaceId) {
		return StringUtils.join(new String[] { Entity.USERRACE.name(), Entity.SEPARATOR, String.valueOf(userRaceId), Entity.SEPARATOR,
				Ship.Field.IncrSid.name() });
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.SHIP, id, field);
	}

	public static String generatorFieldKey(String id, String field) {
		return generatorFieldKey(Entity.SHIP, id, field);
	}

	public Map<String, Resource> getResourcesForMap() {
		return resourcesForMap;
	}

	public void setResourcesForMap(Map<String, Resource> resourcesForMap) {
		this.resourcesForMap = resourcesForMap;
	}

	public Resource getUpgradeCostResource() {
		return upgradeCostResource;
	}

	public void setUpgradeCostResource(Resource upgradeCostResource) {
		this.upgradeCostResource = upgradeCostResource;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public static String generatorShipSetKey(Long userRaceId) {
		return StringUtils.join(new String[] { Entity.USERRACE.name(), Entity.SEPARATOR, String.valueOf(userRaceId), Entity.SEPARATOR,
				UserRace.Field.SHIPS.name() });
	}

}
