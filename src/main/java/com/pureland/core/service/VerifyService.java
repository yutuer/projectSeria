package com.pureland.core.service;

import com.pureland.common.protocal.vo.CampVOProtocal;

/**
 * Created by Administrator on 2015/3/3.
 */
public interface VerifyService {
    public void dealCompareResult(CampVOProtocal.CampVO clientCampVO) throws Exception;
}
