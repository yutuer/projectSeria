package com.pureland.common.service;

import java.util.List;

import com.pureland.common.db.data.Building;
import com.pureland.common.enums.EntityServerTypeEnum;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.MoveBuildingBean;
import com.pureland.common.service.bean.SkillInfo;
import com.pureland.common.util.RuleEx;

/**
 * @author qinpeirong
 */
public interface BuildingCommonService {

	public void initBuilding(Long userRaceId, Integer raceId) throws CoreException;

	public Boolean addBuilding(Long sid, Long userRaceId, Integer cid, Integer abs, Integer ord, long endTime, RuleEx ruleEx) throws CoreException;

	public void updateBuilding(Building building) throws CoreException;

	public Building getBuilding(String sid) throws CoreException;

	public List<Building> getBuildings(Long userRaceId) throws CoreException;

	public List<Building> getBuildingByEntityType(long userRaceId, EntityServerTypeEnum type) throws CoreException;

	public List<Building> getBuildingBySubType(long userRaceId, SubServerTypeEnum type) throws CoreException;

	public int getSameTypeBuildingsLeftSpace(Long userRaceId, SubServerTypeEnum buildingType) throws CoreException;

	public int getSameTypeBuildingsMaxResourceStorage(Long userRaceId, SubServerTypeEnum buildingType) throws CoreException;

	/**
	 * 获取当前资源上限
	 */
	public Integer getResourceUpperLimit(Long userRaceId, ResourceServerTypeEnum resourType);

	public void saveUpgradeSkillInfo(Long userRaceId, SkillInfo skillInfo) throws CoreException;

	public void deleteUpgradeSkillInfo(Long userRaceId) throws CoreException;

	public SkillInfo getUpgradeSkillInfo(Long userRaceId) throws CoreException;

	void moveBuilding(Long userRaceId, List<MoveBuildingBean> moveBuildingBeanList) throws CoreException;

	void updateBrokenTraps(Long userRaceId, List<Long> brokenTraps) throws CoreException;
}
