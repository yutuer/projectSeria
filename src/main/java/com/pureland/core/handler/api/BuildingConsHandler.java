package com.pureland.core.handler.api;

import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO;
import com.pureland.common.protocal.vo.ResourceTypeProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.BuildingService;
import com.pureland.core.service.impl.BuildingServiceImpl;

/**
 * @author qinpeirong
 */
public class BuildingConsHandler extends RequestAPIHandler {

    private BuildingService buildingService = (BuildingService) SpringContextUtil.getBean(BuildingServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        BuildingConsReq buildingConsReq = reqWrapper.getBuildingConsReq();
        ResourceTypeProtocal.ResourceType resourceType = buildingConsReq.getResourceType();
        BuildingVO buildingVO = buildingConsReq.getBuildingVO();
        Long sid = buildingVO.getSid();
        Integer cid = buildingVO.getCid();
        Integer x = buildingVO.getX();
        Integer y = buildingVO.getY();
        long endTime = buildingVO.getEndTime();
        String costType = resourceType.name();

        buildingService.addRuleBuilding(sid, userRaceId, cid, costType, x, y, endTime);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
