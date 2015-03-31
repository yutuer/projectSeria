package com.pureland.seria.service.building;

import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.util.GameUtil;
import com.pureland.seria.db.seriaData.building.Building;

public class BuildCompleteLogicNormal extends BuildCompleteLogicSeria {

	@Override
	void completeBuilding0(BuildingCompleteBean buildingCompleteBean, Building building, EntityModel entityModel) throws CoreException {
		long now = System.currentTimeMillis();
		long buildEndTime = building.getEndTime();
		long sid = building.getSid();
		if (GameUtil.IsTimeWrong(now, buildEndTime)) {
			throw new CoreException("building has not been completed Normal, sid = " + sid);
		}
	}


}
