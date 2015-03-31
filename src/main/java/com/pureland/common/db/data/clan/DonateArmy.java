package com.pureland.common.db.data.clan;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.util.DataObject;

public class DonateArmy extends DataObject {

	private static final long serialVersionUID = 6600971840708682911L;

	private long userRaceId;
	private List<DonateInfo> donateInfoMap = Lists.newArrayList();

	/**
	 * 上一次发起求援请求的时间
	 */
	private long nextCanDonateTime;

	public long getUserRaceId() {
		return userRaceId;
	}

	public void setUserRaceId(long userRaceId) {
		this.userRaceId = userRaceId;
	}

	public List<DonateInfo> getDonateInfoMap() {
		return donateInfoMap;
	}

	public void setDonateInfoMap(List<DonateInfo> donateInfoMap) {
		this.donateInfoMap = donateInfoMap;
	}

	public long getNextCanDonateTime() {
		return nextCanDonateTime;
	}

	public void setNextCanDonateTime(long nextCanDonateTime) {
		this.nextCanDonateTime = nextCanDonateTime;
	}

}
