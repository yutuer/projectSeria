package com.pureland.common.service;

import com.pureland.common.db.data.Resource;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;

import java.util.List;

/**
 *
 * @author qinpeirong
 *
 */
public interface ResourceCommonService {
    public void gather(Long userRaceId, Long buildingId, Long gatherTime,
                        ResourceServerTypeEnum resourceType, Integer resourceCount) throws CoreException;

    public void updateBattleResouce(Long userRaceId, List<Resource> resouces) throws CoreException;

    public void updateResource(Resource resource) throws CoreException;
}
