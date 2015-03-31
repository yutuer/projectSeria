package com.pureland.seria.db.seriaData.building;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.seria.db.seriaData.Product;

public class Building implements Serializable {

	private static final long serialVersionUID = -4643579892187638896L;

	private String buildingId;
	private SubServerTypeEnum buildingType;
	private Long sid;
	private Long userRaceId;
	private int level;
	private BuildingServerStatus status;
	private int cid;
	private int abscissa;
	private int ordinate;
	private long endTime;

	/**
	 * 对不同类型的建筑有不同的意义
	 * <p/>
	 * Research 是研究的cid Trap 0表示可以使用的,1表示使用过的
	 */
	private Integer storageCount;
	private Long gatherTime;

	private List<Product> products;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(buildingId);
		out.writeInt(buildingType.ordinal());
		out.writeLong(sid);
		out.writeLong(userRaceId);
		out.writeInt(level);
		out.writeInt(status.ordinal());
		out.writeInt(cid);
		out.writeInt(abscissa);
		out.writeInt(ordinate);
		out.writeLong(endTime);
		out.writeInt(storageCount);
		out.writeLong(gatherTime);
		out.writeObject(products);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		buildingId = in.readUTF();
		buildingType = SubServerTypeEnum.values()[in.readInt()];
		level = in.readInt();
		status = BuildingServerStatus.values()[in.readInt()];
		cid = in.readInt();
		abscissa = in.readInt();
		ordinate = in.readInt();
		endTime = in.readLong();
		storageCount = in.readInt();
		gatherTime = in.readLong();
		products = (List<Product>) in.readObject();
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public SubServerTypeEnum getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(SubServerTypeEnum type) {
		this.buildingType = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BuildingServerStatus getStatus() {
		return status;
	}

	public void setStatus(BuildingServerStatus status) {
		this.status = status;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getAbscissa() {
		return abscissa;
	}

	public void setAbscissa(int abscissa) {
		this.abscissa = abscissa;
	}

	public int getOrdinate() {
		return ordinate;
	}

	public void setOrdinate(int ordinate) {
		this.ordinate = ordinate;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public Integer getStorageCount() {
		return storageCount;
	}

	public void setStorageCount(Integer storageCount) {
		this.storageCount = storageCount;
	}

	public Long getGatherTime() {
		return gatherTime;
	}

	public void setGatherTime(Long gatherTime) {
		this.gatherTime = gatherTime;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
