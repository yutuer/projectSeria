package com.pureland.common.service.bean;

/**
 * Created by Administrator on 2015/2/10.
 */
public class MoveBuildingBean {

    private final Long sid;
    private final int x;
    private final int y;

    public MoveBuildingBean(Long sid, int x, int y) {
        this.sid = sid;
        this.x = x;
        this.y = y;
    }

    public Long getSid() {
        return sid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
