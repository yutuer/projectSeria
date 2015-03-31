package com.pureland.core.service;

import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;

/**
 * Created by Administrator on 2015/2/10.
 */
public interface ResourceService {

    public void buyResource(Long userRaceId, ResourceServerTypeEnum resourceServerType, int count) throws CoreException;

}
