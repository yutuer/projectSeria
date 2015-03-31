package com.pureland.core.handler.api;

import com.google.common.collect.Lists;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.MoveBuildingReqProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.BuildingVOProtocal;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.bean.MoveBuildingBean;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;

import java.util.List;

/**
 * @author qinpeirong
 */
public class MoveBuildingHandler extends RequestAPIHandler {

    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp)
            throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        MoveBuildingReqProtocal.MoveBuildingReq moveBuildingReq = reqWrapper.getMoveBuildingReq();
        List<BuildingVOProtocal.BuildingVO> buildingVOList = moveBuildingReq.getBuildingList();
        List<MoveBuildingBean> moveBuildingBeanList = Lists.newArrayList();
        for (BuildingVOProtocal.BuildingVO buildingVO : buildingVOList) {
            MoveBuildingBean moveBuildingBean = new MoveBuildingBean(buildingVO.getSid(), buildingVO.getX(), buildingVO.getY());
            moveBuildingBeanList.add(moveBuildingBean);
        }
        buildingCommonService.moveBuilding(userRaceId, moveBuildingBeanList);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }


}
