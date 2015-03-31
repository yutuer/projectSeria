package com.pureland.core.service;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;

import java.util.List;

/**
 * @author qinpeirong
 */
public interface UserRaceService {

    public Long addUserRace(Long userId, Integer raceId, String nickName) throws CoreException;

    public List<UserRace> getUserRaces(Long userId, Boolean sort) throws CoreException;

    public UserRace getLastUserRace(Long userId) throws CoreException;

    public void updateLastLoginTime(Long userRaceId) throws CoreException;

    public UserRace getUserRace(Long userRaceId) throws CoreException;

    public void updateAllBuildingArmyCid(Long userRaceId, Integer beforeCid, Integer upgradeCid) throws CoreException;

    public void updateAllBuildingSkillCid(Long userRaceId, Integer beforeCid, Integer upgradeCid) throws CoreException;
}
