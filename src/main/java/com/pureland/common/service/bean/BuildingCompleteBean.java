package com.pureland.common.service.bean;

import com.pureland.common.enums.BuildingServerCompleteEnum;

/**
 * Created by Administrator on 2015/2/2.
 */
public class BuildingCompleteBean {
    private final long userRaceId;
    private final long sid;
    private final BuildingServerCompleteEnum completeType;
    private final long nowTime;

    public BuildingCompleteBean(long userRaceId, long sid, BuildingServerCompleteEnum completeType, long nowTime) {
        this.userRaceId = userRaceId;
        this.sid = sid;
        this.completeType = completeType;
        this.nowTime = nowTime;
    }

    public BuildingServerCompleteEnum getCompleteType() {
        return completeType;
    }

    public long getNowTime() {
        return nowTime;
    }

    public long getSid() {
        return sid;
    }

    public long getUserRaceId() {
        return userRaceId;
    }
}
