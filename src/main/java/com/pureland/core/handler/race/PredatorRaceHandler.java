package com.pureland.core.handler.race;

import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.UserRaceHandler;

public class PredatorRaceHandler extends UserRaceHandler {
	private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
	private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());

	@Override
	public void initCamp(Long userRaceId, Integer raceId) throws CoreException {
		userExtCommonService.initUserExt(userRaceId);
		buildingCommonService.initBuilding(userRaceId, raceId);
	}

}
