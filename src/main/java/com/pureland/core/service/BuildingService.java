package com.pureland.core.service;

import com.pureland.common.enums.RefillServerType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.service.bean.BuildingUpgradeBean;
import com.pureland.common.util.RuleEx;

public interface BuildingService {

	public void addRuleBuilding(Long sid, Long userRaceId, Integer cid, String costType, Integer abs, Integer ord, long endTime) throws CoreException;

	public void upgradeBuilding(BuildingUpgradeBean buildingUpgradeBean) throws CoreException;

	public void completeBuilding(BuildingCompleteBean buildingCompleteBean) throws CoreException;

	public void dealBuildingsComleteProduct(final Long userRaceId) throws CoreException;

	public RuleEx getBuildingRuler(BuildingBean validateModel, BuildingRulerEnum rulerType) throws CoreException;

	void refillTrap(Long userRaceId, Long sid, RefillServerType refillServerType) throws CoreException;

	public enum BuildingRulerEnum {
		Build, Upgrade
	}
}
