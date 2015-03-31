package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/2/3.
 */
public class ResourceCostBean {

    private final Long userRaceId;
    private final String costType;
    private final Integer count;

    public String getCostType() {
        return costType;
    }

    public Integer getCount() {
        return count;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }

    public ResourceCostBean(Long userRaceId, String costType, Integer count) {
        this.costType = costType;
        this.userRaceId = userRaceId;
        this.count = count;
    }
}
