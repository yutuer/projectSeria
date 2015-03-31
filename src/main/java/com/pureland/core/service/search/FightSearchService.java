package com.pureland.core.service.search;

import com.pureland.common.error.CoreException;

/**
 * Created by qinpeirong on 15-1-16.
 */
public interface FightSearchService {
    public Long fightSearch(Long userId, Long userRaceId) throws CoreException;
}
