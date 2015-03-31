package com.pureland.common.db.data.clan;

import com.pureland.common.util.DataObject;

public class DonateInfo extends DataObject {
	private static final long serialVersionUID = -2768469779151135893L;
	private Long userRaceId;
	private Integer cid;
	private Integer num;

	public Long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(Long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
