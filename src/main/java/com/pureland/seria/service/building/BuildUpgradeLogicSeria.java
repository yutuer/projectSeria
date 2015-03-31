package com.pureland.seria.service.building;

import com.pureland.seria.db.seriaData.building.Building;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingUpgradeBean;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.seria.db.dao.BuildingSeriaDAO;
import com.pureland.seria.module.BuildingModule;

public abstract class BuildUpgradeLogicSeria {
	private BuildingSeriaDAO buildingSeriaDAO = (BuildingSeriaDAO) SpringContextUtil.getBean(BuildingSeriaDAO.class.getSimpleName());

	public void upgradeBuilding(BuildingUpgradeBean buildingUpgradeBean) throws CoreException {
		Long userRaceId = buildingUpgradeBean.getUserRaceId();
		Long sid = buildingUpgradeBean.getSid();

		String buildingId = "";
		BuildingModule bm = buildingSeriaDAO.getBuildingModule(userRaceId);
		Building building = bm.getBuilding(buildingId);
		if (building == null) {
			throw new CoreException("building is null, id = " + sid);
		}
		upgradeBuilding0(buildingUpgradeBean, building);
	}

	protected abstract void upgradeBuilding0(BuildingUpgradeBean buildingUpgradeBean, Building building) throws CoreException;

}
