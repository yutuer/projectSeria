package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/2/2.
 */
public class BuildingUpgradeBean {
    private final Long userRaceId;
    private final Long sid;
    private final String costType;
    private final long endTime;
    private final boolean isCancel;

    public BuildingUpgradeBean(Long userRaceId, Long sid, String costType, long endTime, boolean isCancel) {
        this.costType = costType;
        this.userRaceId = userRaceId;
        this.sid = sid;
        this.endTime = endTime;
        this.isCancel = isCancel;
    }

    public String getCostType() {
        return costType;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public Long getSid() {
        return sid;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }
}
