package com.pureland.common.service;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.CampRespProtocal;

/**
 * Created by Administrator on 2015/2/10.
 */
public interface CampCommonService {

    public CampRespProtocal.CampResp buildCampResp(UserRace userRace) throws CoreException;

}
