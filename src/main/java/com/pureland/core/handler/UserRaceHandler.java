package com.pureland.core.handler;

import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public abstract class UserRaceHandler {
    public abstract void initCamp(Long userRaceId, Integer raceId) throws CoreException;
}
