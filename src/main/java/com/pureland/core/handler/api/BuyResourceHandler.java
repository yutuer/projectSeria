package com.pureland.core.handler.api;

import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.BuyResourceProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.protocal.vo.ResourceVOProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.ResourceService;
import com.pureland.core.service.impl.ResourceServiceImpl;

/**
 * @author qinpeirong
 */
public class BuyResourceHandler extends RequestAPIHandler {

    private ResourceService resourceService = (ResourceService) SpringContextUtil.getBean(ResourceServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp)
            throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        BuyResourceProtocal.BuyResourceReq buyResourceReq = reqWrapper.getBuyResourceReq();
        ResourceVOProtocal.ResourceVO resourceVO = buyResourceReq.getResourceVO();
        ResourceServerTypeEnum resourceServerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(resourceVO.getResourceType().name());
        int count = resourceVO.getResourceCount();
        resourceService.buyResource(userRaceId, resourceServerType, count);
        RespWrapper respWrapper = RespWrapper.newBuilder().build();
        return respWrapper;
    }


}
