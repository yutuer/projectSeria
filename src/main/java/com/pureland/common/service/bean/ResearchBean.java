package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/1/31.
 */
public class ResearchBean {

    private final long userRaceId;
    private final int cid;
    private final long currentTime;
    private final int diamondCount;

    public ResearchBean(long userRaceId, int cid, long currentTime, int diamondCount) {
        this.userRaceId = userRaceId;
        this.cid = cid;
        this.currentTime = currentTime;
        this.diamondCount = diamondCount;
    }

    public int getCid() {
        return cid;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public int getDiamondCount() {
        return diamondCount;
    }

    public long getUserRaceId() {
        return userRaceId;
    }
}
