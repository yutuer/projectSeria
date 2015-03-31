package com.pureland.core.handler.api;

import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.GatherReqProtocal.GatherReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType;
import com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;

/**
 * @author qinpeirong
 */
public class GatherHandler extends RequestAPIHandler {

    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken,
                               Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        GatherReq gatherReq = reqWrapper.getGatherReq();
        Long sid = gatherReq.getSid();
        Long gatherTime = gatherReq.getGatherTime();
        ResourceVO resourceVO = gatherReq.getResourceVO();
        ResourceType resourceType = resourceVO.getResourceType();
        int resourceCount = resourceVO.getResourceCount();
        ResourceServerTypeEnum resourceServcerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(resourceType.name());
        resourceCommonService.gather(userRaceId, sid, gatherTime, resourceServcerType, resourceCount);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }

}
