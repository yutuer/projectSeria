package com.pureland.core.service.building;

import com.pureland.common.db.data.Building;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.service.impl.WorkerQueueCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.UserExtService;
import com.pureland.core.service.impl.BuildingServiceImpl;
import com.pureland.core.service.impl.UserExtServiceImpl;
import com.pureland.common.service.bean.BuildingUpgradeBean;

public abstract class BuildUpgradeLogic {

	protected UserExtService userExtService = (UserExtService) SpringContextUtil.getBean(UserExtServiceImpl.class.getSimpleName());
	protected UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	protected BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
	protected WorkerQueueCommonService queueService = (WorkerQueueCommonService) SpringContextUtil.getBean(WorkerQueueCommonServiceImpl.class.getSimpleName());
	protected BuildingService buildingService = (BuildingService) SpringContextUtil.getBean(BuildingServiceImpl.class.getSimpleName());

	public void upgradeBuilding(BuildingUpgradeBean buildingUpgradeBean) throws CoreException {
		Long userRaceId = buildingUpgradeBean.getUserRaceId();
		Long sid = buildingUpgradeBean.getSid();

		String buildingId = Building.getIdKeyString(userRaceId, sid);
		Building building = buildingCommonService.getBuilding(buildingId);
		if (building == null) {
			throw new CoreException("building is null, id = " + sid);
		}
		upgradeBuilding0(buildingUpgradeBean, building);
	}

	protected abstract void upgradeBuilding0(BuildingUpgradeBean buildingUpgradeBean, Building building) throws CoreException;

}
