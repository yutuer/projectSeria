package com.pureland.common.error;

/**
 * Created by Administrator on 2015/2/12.
 */
public class InFightException extends RuntimeException {
    private Long userRaceId;

    public InFightException(Long userRaceId) {
        this.userRaceId = userRaceId;
    }
}
