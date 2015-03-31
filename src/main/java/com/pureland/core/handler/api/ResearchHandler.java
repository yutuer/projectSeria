package com.pureland.core.handler.api;

import com.pureland.common.enums.ResearchRequestServerType;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.ResearchReqProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.ResearchService;
import com.pureland.core.service.impl.ResearchServiceImpl;

/**
 * @author qinpeirong
 */
public class ResearchHandler extends RequestAPIHandler {

    private ResearchService researchService = (ResearchService) SpringContextUtil.getBean(ResearchServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken,
                               Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        ResearchReqProtocal.ResearchReq researchReq = reqWrapper.getResearchReq();
        ResearchRequestServerType researchRequestType = ResearchRequestServerType.getResearchRequestServerTypeByNumber(researchReq.getResearchRequestType().getNumber());
        int cid = researchReq.getCid();
        long currentTime = researchReq.getCurrentTime();
        int diamondCount = researchReq.getDiamondCount();
        researchService.research(userRaceId, researchRequestType, cid, currentTime, diamondCount);
        RespWrapper respWrapper = RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
