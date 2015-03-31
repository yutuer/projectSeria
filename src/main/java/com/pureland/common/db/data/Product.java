package com.pureland.common.db.data;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class Product extends DataObject {

	private static final long serialVersionUID = -1460579452250080357L;

	private Long id;
	private Integer cid;
	private Long userRaceId;
	private Long buildingSid;
	private Integer amount;
	private Long beginTime; // 建造一种单位的开始时间(s)
	private Long nextEndTime;

	public static String generatorIdKey(String... singleMark) {
		return generatorIdKey(Entity.PRODUCT, Entity.Product.ID.getName(), StringUtils.join(singleMark, Entity.SEPARATOR));
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.PRODUCT, id, field);
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
	 * @return the buildingSid
	 */
	public Long getBuildingSid() {
		return buildingSid;
	}

	/**
	 * @param buildingSid
	 *            the buildingSid to set
	 */
	public void setBuildingSid(Long buildingSid) {
		this.buildingSid = buildingSid;
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
	 * @return the endTime
	 */
	public Long getNextEndTime() {
		return nextEndTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setNextEndTime(Long endTime) {
		this.nextEndTime = endTime;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
}
