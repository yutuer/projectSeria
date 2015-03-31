package com.pureland.core.service.building;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.error.CoreException;
import com.pureland.common.util.RuleEx;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.service.BuildingService;
import com.pureland.common.service.bean.BuildingUpgradeBean;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.common.service.bean.BuildingBean;

public class BuildUpgradeLogicNormal extends BuildUpgradeLogic {

	@Override
	protected void upgradeBuilding0(BuildingUpgradeBean buildingUpgradeBean, Building building) throws CoreException {
		Long userRaceId = buildingUpgradeBean.getUserRaceId();
		Long sid = buildingUpgradeBean.getSid();
		long endTime = buildingUpgradeBean.getEndTime();
		String costType = buildingUpgradeBean.getCostType();

		BuildingServerStatus status = building.getStatus();
		if (!BuildingServerStatus.ON.getId().equals(status.getId())) {
			throw new CoreException("upgradeBuilding status is illegal, sid = " + buildingUpgradeBean.getSid() + ", status = " + status.getName());
		}
		// 验证是否不在workqueue队列中
		if (queueService.isInWorkerQueues(userRaceId, sid)) {
			throw new CoreException(String.format("在工作队列中 ,sid:%d \n", sid));
		}

		Integer cid = building.getCid();
		EntityModel em = EntityModelHelper.ENTITIES.get(cid);
		int upgradeId = em.getUpgradeId();

		EntityModel entityModel = EntityModelHelper.ENTITIES.get(upgradeId);
		if (entityModel.getBuildTime() == 0) {
			building.setCid(upgradeId);
			building.setStatus(BuildingServerStatus.ON);
			building.setEndTime(endTime);

			// 给玩家加经验
			int addExp = entityModel.getBuildExp();
			userExtService.addExp(userRaceId, addExp);
		} else {
			building.setStatus(BuildingServerStatus.UPGRADE);
			building.setEndTime(endTime);
		}
		// 这里传入的是升级后的cid
		BuildingBean vm = new BuildingBean(userRaceId, sid, upgradeId, costType);
		RuleEx upgradeBuildingRuler = buildingService.getBuildingRuler(vm, BuildingService.BuildingRulerEnum.Upgrade);
		if (upgradeBuildingRuler.rule()) {
			buildingCommonService.updateBuilding(building);
		} else {
			throw new CoreException("upgrade building failed, sid = " + sid + ", cid = " + cid);
		}

		// 资源扣减
		userRaceCommonService.resourceAdd(new ResourceCostBean(userRaceId, costType, -1 * entityModel.getCostResourceCount()));
		// 只有需要建造时间,才入队
		if (entityModel.getBuildTime() > 0) {
			queueService.addWorkerQuene(userRaceId, sid);
		}
	}
}
