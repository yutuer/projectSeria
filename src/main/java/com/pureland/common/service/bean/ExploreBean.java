package com.pureland.common.service.bean;

import com.pureland.common.enums.ResourceServerTypeEnum;

/**
 * Created by Administrator on 2015/3/6.
 */
public class ExploreBean {
    private final Long userRaceId;
    private Long sid;
    private final Integer section;
    private final ResourceServerTypeEnum consumeResourceServerType;
    private final int consumeCount;

    public ExploreBean(Long userRaceId, Long sid, Integer section, ResourceServerTypeEnum consumeResourceServerType, int consumeCount) {
        this.consumeCount = consumeCount;
        this.userRaceId = userRaceId;
        this.sid = sid;
        this.section = section;
        this.consumeResourceServerType = consumeResourceServerType;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUserRaceId() {
        return userRaceId;
    }

    public Integer getSection() {
        return section;
    }

    public Long getSid() {
        return sid;
    }

    public int getConsumeCount() {
        return consumeCount;
    }

    public ResourceServerTypeEnum getConsumeResourceServerType() {
        return consumeResourceServerType;
    }
}
