package com.pureland.core.service;

import com.pureland.common.enums.ResearchRequestServerType;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.ResearchReqProtocal;

/**
 * Created by Administrator on 2015/1/30.
 */
public interface ResearchService {
    public void research(Long userRaceId, ResearchRequestServerType researchRequestType, int cid, long currentTime, int diamondCount) throws CoreException;
}