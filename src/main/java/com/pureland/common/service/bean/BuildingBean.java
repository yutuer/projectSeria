package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/1/27.
 */
public class BuildingBean {
    private final Long userRaceId;
    private final Integer cid;
    private final String costType;
    private final Long  sid;

    public BuildingBean(Long userRaceId, Long sid, Integer cid, String costType) {
        this.cid = cid;
        this.userRaceId = userRaceId;
        this.costType = costType;
        this.sid = sid;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }


    public Integer getCid() {
        return cid;
    }


    public String getCostType() {
        return costType;
    }

    public Long getSid() {
        return sid;
    }
}
