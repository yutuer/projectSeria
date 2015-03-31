package com.pureland.seria.db.seriaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 3192379911169199407L;

	private Long productId;
	private Integer cid;
	private Long userRaceId;
	private Long buildingSid;
	private Integer amount;
	private Long beginTime; // 建造一种单位的开始时间(s)
	private Long nextEndTime;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(productId);
		out.writeInt(cid);
		out.writeLong(userRaceId);
		out.writeLong(buildingSid);
		out.writeInt(amount);
		out.writeLong(beginTime);
		out.writeLong(nextEndTime);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		productId = in.readLong();
		cid = in.readInt();
		userRaceId = in.readLong();
		buildingSid = in.readLong();
		amount = in.readInt();
		beginTime = in.readLong();
		nextEndTime = in.readLong();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public Long getBuildingSid() {
		return buildingSid;
	}

	public void setBuildingSid(Long buildingSid) {
		this.buildingSid = buildingSid;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getNextEndTime() {
		return nextEndTime;
	}

	public void setNextEndTime(Long nextEndTime) {
		this.nextEndTime = nextEndTime;
	}
}
