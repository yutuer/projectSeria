package com.pureland.common.db.data;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;
import org.apache.commons.lang3.StringUtils;

public class Skill extends DataObject {

	private static final long serialVersionUID = -2637737004952813050L;

	private Long id;
	private Long userRaceId;
	private Integer cid;
	private Integer amount;
	private Integer totalAmount;

	public static String generatorIdKey(String... singleMark) {
		return generatorIdKey(Entity.SKILL, Entity.Army.ID.getName(), StringUtils.join(singleMark, Entity.SEPARATOR));
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.SKILL, id, field);
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
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
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

	/**
	 * @return the totalAmount
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

}
