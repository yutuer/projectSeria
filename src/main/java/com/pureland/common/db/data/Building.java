package com.pureland.common.db.data;

import java.util.List;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.Entity;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.util.DataObject;
import com.pureland.core.init.EntityModelHelper;

public class Building extends DataObject {

	private static final long serialVersionUID = -711884988579438157L;

	private String id;
	private Long sid;
	private Integer cid;
	private Long userRaceId;
	private BuildingServerStatus status;
	private SubServerTypeEnum buildingType;
	private Integer abscissa;
	private Integer ordinate;
	private Long endTime;
	/**
	 * 对不同类型的建筑有不同的意义
	 * <p/>
	 * Research 是研究的cid Trap 0表示可以使用的,1表示使用过的
	 */
	private Integer storageCount;
	private Long gatherTime;

	private List<Product> products;

	public EntityModel getEntityModel() {
		EntityModel em = EntityModelHelper.ENTITIES.get(cid);
		return em;
	}

	private static String getIdByUserRace(Long userRaceId) {
		StringBuffer key = new StringBuffer();
		key.append(Entity.BUILDING.getName()).append(userRaceId);
		return key.toString();
	}

	public static Long getIdValueWithUserRace(Long userRaceId) throws RedisException {
		String key = getIdByUserRace(userRaceId);
		return new Long(RString.get(key));
	}

	public static String getIdKeyString(Long userRaceId, Long buildingSid) {
		return generatorFieldKey(Entity.BUILDING, userRaceId.toString(), buildingSid.toString());
	}

	public static Long generatorIdWithUserRace(Long userRaceId) throws RedisException {
		return RString.generator(getIdByUserRace(userRaceId));
	}

	public static String generatorFieldKey(String id, String field) {
		return generatorFieldKey(Entity.BUILDING, id, field);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
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

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the status
	 */
	public BuildingServerStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(BuildingServerStatus status) {
		this.status = status;
	}

	/**
	 * @return the abscissa
	 */
	public Integer getAbscissa() {
		return abscissa;
	}

	/**
	 * @param abscissa
	 *            the abscissa to set
	 */
	public void setAbscissa(Integer abscissa) {
		this.abscissa = abscissa;
	}

	/**
	 * @return the ordinate
	 */
	public Integer getOrdinate() {
		return ordinate;
	}

	/**
	 * @param ordinate
	 *            the ordinate to set
	 */
	public void setOrdinate(Integer ordinate) {
		this.ordinate = ordinate;
	}

	/**
	 * @return the buildingType
	 */
	public SubServerTypeEnum getBuildingType() {
		return buildingType;
	}

	/**
	 * @param buildingType
	 *            the buildingType to set
	 */
	public void setBuildingType(SubServerTypeEnum buildingType) {
		this.buildingType = buildingType;
	}

	/**
	 * @return the gatherTime
	 */
	public Long getGatherTime() {
		return gatherTime;
	}

	/**
	 * @param gatherTime
	 *            the gatherTime to set
	 */
	public void setGatherTime(Long gatherTime) {
		this.gatherTime = gatherTime;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getStorageCount() {
		return storageCount;
	}

	public void setStorageCount(Integer storageCount) {
		this.storageCount = storageCount;
	}

}
