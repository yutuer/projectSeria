package com.pureland.core.service.building;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingUpgradeBean;
import com.pureland.common.service.bean.ResourceCostBean;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/2/3.
 */
public class BuildUpgradeLogicCancel extends BuildUpgradeLogic {
    @Override
    protected void upgradeBuilding0(BuildingUpgradeBean buildingUpgradeBean, Building building) throws CoreException {
        Long userRaceId = buildingUpgradeBean.getUserRaceId();
        Long sid = buildingUpgradeBean.getSid();
        long endTime = buildingUpgradeBean.getEndTime();
        String costType = buildingUpgradeBean.getCostType();


        BuildingServerStatus status = building.getStatus();
        if (!BuildingServerStatus.UPGRADE.getId().equals(status.getId())) {
            throw new CoreException("cancel upgradeBuilding status is illegal, sid = " + sid + ", status = " + status.getName());
        }
        //验证是否在workqueue队列中
        if (!queueService.isInWorkerQueues(userRaceId, sid)) {
            throw new CoreException(String.format("不在工作队列中 ,sid:%d \n", sid));
        }

        Integer cid = building.getCid();
        EntityModel em = EntityModelHelper.ENTITIES.get(cid);
        int upgradeId = em.getUpgradeId();
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(upgradeId);
        //修改建筑状态
        building.setStatus(BuildingServerStatus.ON);
        buildingCommonService.updateBuilding(building);
        //返还一半的消耗
        userRaceCommonService.resourceAdd(new ResourceCostBean(userRaceId, costType, entityModel.getCostResourceCount() / 2));
        //出队
        queueService.removeWorkerQuene(userRaceId, sid);
    }
}
