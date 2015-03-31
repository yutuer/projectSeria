package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/2/4.
 */
public class ProductBean {
    private final Long userRaceId;
    private final Long buildingSid;
    private final Integer cid;
    private final Long timestamp;
    private final Integer amount;
    private final Long endTime;
    private final Integer diamondCount;

    public ProductBean(Long userRaceId, Long buildingSid, Integer cid, Long timestamp, Integer amount, Long endTime, Integer diamondCount) {
        this.amount = amount;
        this.userRaceId = userRaceId;
        this.buildingSid = buildingSid;
        this.cid = cid;
        this.timestamp = timestamp;
        this.endTime = endTime;
        this.diamondCount = diamondCount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getBuildingSid() {
        return buildingSid;
    }

    public Integer getCid() {
        return cid;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }

    public Integer getDiamondCount() {
        return diamondCount;
    }
}
