package com.pureland.common.db.data;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class Army extends DataObject {

	private static final long serialVersionUID = -2637737004952813050L;

	public static enum Field {
		ID, USERRACEID, ARMYEXPID, AMOUNT;
	}

	private Long id;
	private Long userRaceId;
	private Long armyExpId;
	private Integer amount;

	public static String generatorIdKey(String... singleMark) {
		return generatorIdKey(Entity.ARMY, Army.Field.ID.name(), StringUtils.join(singleMark, Entity.SEPARATOR));
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.ARMY, id, field);
	}

	public static String getArmyIdKeyString(Long userRaceId) {
		return generatorIdKey(Entity.PLAYER, Entity.ARMY.name(), String.valueOf(userRaceId));
	}

	public Long getArmyExpId() {
		return armyExpId;
	}

	public void setArmyExpId(Long armyExpId) {
		this.armyExpId = armyExpId;
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
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
