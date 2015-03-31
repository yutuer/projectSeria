package com.pureland.core.service.building;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.util.GameUtil;

public class BuildCompleteLogicNormal extends BuildCompleteLogic {

	@Override
	public void completeBuilding0(BuildingCompleteBean buildingCompleteBean, Building building, EntityModel entityModel) throws CoreException {
		long now = System.currentTimeMillis();
		long buildEndTime = building.getEndTime();
		long sid = building.getSid();
		if (GameUtil.IsTimeWrong(now, buildEndTime)) {
			throw new CoreException("building has not been completed Normal, sid = " + sid);
		}
	}
}
