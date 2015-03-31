package com.pureland.common.service;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.common.service.bean.SkillInfo;

import java.util.List;
import java.util.Set;

/**
 * @author qinpeirong
 */
public interface UserRaceCommonService {

    public Long addUserRace(UserRace userRace) throws CoreException;

    public List<UserRace> getUserRaces(Long userId, Boolean sort) throws CoreException;

    public UserRace getUserRace(Long userRaceId) throws CoreException;

    public void updateUserRace(UserRace userRace) throws CoreException;

    public void updateNickName(String nickName) throws CoreException;

    public Set<String> getAllOfflineUserRaceIds() throws CoreException;

    public Set<String> getSkillShop(Long userRaceId) throws CoreException;

    public void updateSkillShopOneSkillId(Long userRaceId, String skillId, String upgradeSkillId) throws CoreException;

    /**
     * 增加的资源, 里面的数量如果为负数则为减法
     *
     * @param resourceCostBean
     * @throws CoreException
     */
    public void resourceAdd(ResourceCostBean resourceCostBean) throws CoreException;

    public void removeSkillIdFromSkillShop(Long userRaceId, String skillId) throws CoreException;

    public void addSkillId2SkillShop(Long userRaceId, String skillId) throws CoreException;

    public char[][] getBuildingSpaceInfo(Long userRaceId) throws CoreException;

    public void setBuildingSpaceInfo(Long userRaceId, char[][] buildingSpaceInfo) throws CoreException;

    public void updateLastOperateTime(Long userRaceId) throws CoreException;

    public Double getLastOperateTime(Long userRaceId) throws CoreException;

    public void updateUserRaceFightStatus(Long userRaceId, boolean isInFight) throws CoreException;

    public boolean isUserRaceInFight(Long userRaceId) throws CoreException;

    public void updateUserRaceFightOpponent(Long userRaceId, Long opponentUserRaceId) throws CoreException;

    public Long getUserRaceFightOpponent(Long userRaceId) throws CoreException;
}
