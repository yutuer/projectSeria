package com.pureland.common.error;

/**
 * Created by Administrator on 2015/2/12.
 */
public class InOffLineException extends RuntimeException {
    
    private Long userRaceId;

    public InOffLineException(Long userRaceId) {
        this.userRaceId = userRaceId;
    }
}
