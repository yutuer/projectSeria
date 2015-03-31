package com.pureland.core.service;

import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;

/**
 * Created by qinpeirong on 15-1-20.
 */
public interface UserExtService {
    public void updateCrown(Long userRaceId, Integer crown) throws CoreException;

    public void addExp(Long userRaceId, int addExp) throws CoreException;

    void chargeDiamond(Long userRaceId, int count) throws CoreException;
}
