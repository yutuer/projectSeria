package com.pureland.core.handler.api;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.CampReqProtocal.CampReq;
import com.pureland.common.protocal.CampRespProtocal.CampResp;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.service.CampCommonService;
import com.pureland.common.service.impl.CampCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.UserRaceService;
import com.pureland.core.service.impl.BuildingServiceImpl;
import com.pureland.core.service.impl.UserRaceServiceImpl;

/**
 * @author qinpeirong
 */
public class CampHandler extends RequestAPIHandler {

	private UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
	private CampCommonService campCommonService = (CampCommonService) SpringContextUtil.getBean(CampCommonServiceImpl.class.getSimpleName());
	private BuildingService buildingService = (BuildingService) SpringContextUtil.getBean(BuildingServiceImpl.class.getSimpleName());

	@Override
	public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		Long authUserId = authUser(authToken);
		Long userRaceId = super.getLastUserRaceId(authUserId);
		CampReq campReq = reqWrapper.getCampReq();
		Long userId = campReq.getUserId();
		if (userRaceId != userId) {
			// 说明是查看別人,需要判断是否是好友
		} else {
			// 是查看自己
			// 登录后处理下线的时候,建筑以及军队建造情况
			buildingService.dealBuildingsComleteProduct(userRaceId);
		}

		UserRace userRace = userRaceService.getUserRace(userRaceId);

		if (userRace == null) {
			throw new CoreException("userId is mapping null, " + userId);
		}
		CampResp campResp = campCommonService.buildCampResp(userRace);
		RespWrapper respWrapper = RespWrapper.newBuilder().setCampResp(campResp).build();
		PurelandLog.info(reqWrapper.toString());
		return respWrapper;
	}

}
