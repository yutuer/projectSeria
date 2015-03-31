package com.pureland.seria.service.building;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.service.impl.WorkerQueueCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.UserExtService;
import com.pureland.core.service.impl.UserExtServiceImpl;
import com.pureland.seria.db.dao.BuildingSeriaDAO;
import com.pureland.seria.db.seriaData.building.Building;
import com.pureland.seria.module.BuildingModule;

public abstract class BuildCompleteLogicSeria {
	private BuildingSeriaDAO buildingSeriaDAO = (BuildingSeriaDAO) SpringContextUtil.getBean(BuildingSeriaDAO.class.getSimpleName());
	protected UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	protected UserExtService userExtService = (UserExtService) SpringContextUtil.getBean(UserExtServiceImpl.class.getSimpleName());
	protected BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
	protected WorkerQueueCommonService queueService = (WorkerQueueCommonService) SpringContextUtil.getBean(WorkerQueueCommonServiceImpl.class.getSimpleName());

	public void completeBuilding(BuildingCompleteBean buildingCompleteBean) throws CoreException {
		Long userRaceId = buildingCompleteBean.getUserRaceId();
		Long sid = buildingCompleteBean.getSid();

		String buildingId = "";
		BuildingModule bm = buildingSeriaDAO.getBuildingModule(userRaceId);
		Building building = bm.getBuilding(buildingId);
		if (building == null) {
			throw new CoreException("no building, sid = " + sid);
		}
		Integer cid = building.getCid();
		BuildingServerStatus status = building.getStatus();
		Long statusId = status.getId();
		if (BuildingServerStatus.ON.getId().equals(statusId)) {
			throw new CoreException("building status is illegal, sid = " + sid + ", status = " + status.getName());
		}

		EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
		if (BuildingServerStatus.UPGRADE.getId().equals(statusId)) {
			int upgradeId = entityModel.getUpgradeId();
			entityModel = EntityModelHelper.ENTITIES.get(upgradeId);
		}
		completeBuilding0(buildingCompleteBean, building, entityModel);
		if (BuildingServerStatus.UPGRADE.getId().equals(statusId)) {
			// 如果是升级完成则需要更改建筑id
			building.setCid(entityModel.getBaseId());
			// 如果是base,则修改playerVO上的baseId
			if (entityModel.getSubType().equals(SubServerTypeEnum.Base.name())) {
				UserRace ur = new UserRace();
				ur.setId(userRaceId);
				ur.setCampCid(entityModel.getBaseId());
				userRaceCommonService.updateUserRace(ur);
			}
		}

		building.setStatus(BuildingServerStatus.ON);
		buildingSeriaDAO.updateBuilding(building);
		// 出队
		queueService.removeWorkerQuene(userRaceId, sid);
		// 给玩家加经验
		int addExp = entityModel.getBuildExp();
		userExtService.addExp(userRaceId, addExp);
	}

	abstract void completeBuilding0(BuildingCompleteBean buildingCompleteBean, Building building, EntityModel entityModel) throws CoreException;
}
