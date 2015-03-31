package com.pureland.core.handler.api;

import com.pureland.common.enums.BuildingServerCompleteEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BuildingCompleteReqProtocal.BuildingCompleteReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.impl.BuildingServiceImpl;
import com.pureland.common.service.bean.BuildingCompleteBean;

/**
 * @author qinpeirong
 */
public class BuildingCompleteHandler extends RequestAPIHandler {
    private BuildingService buildingService = (BuildingService) SpringContextUtil.getBean(BuildingServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp)
            throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        BuildingCompleteReq buildingCompleteReq = reqWrapper.getBuildingCompleteReq();
        Long sid = buildingCompleteReq.getSid();
        BuildingServerCompleteEnum completeType = BuildingServerCompleteEnum.getBuildingServerCompleteEnumByIndex(buildingCompleteReq.getCompleteType().getNumber());
        Long nowTime = buildingCompleteReq.getTimestamp();
        buildingService.completeBuilding(new BuildingCompleteBean(userRaceId, sid, completeType, nowTime));
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
