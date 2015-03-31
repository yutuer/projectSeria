package com.pureland.core.handler.api;

import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.ChargeDiamondProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserExtService;
import com.pureland.core.service.impl.UserExtServiceImpl;

/**
 * Created by Administrator on 2015/2/11.
 */
public class ChargeDiamondHandler extends RequestAPIHandler {

    private UserExtService userExtService = (UserExtService) SpringContextUtil.getBean(UserExtServiceImpl.class.getSimpleName());

    @Override
    public RespWrapperProtocal.RespWrapper handler(ReqWrapperProtocal.ReqWrapper reqWrapper, String authToken, Long timestamp)
            throws CoreException {
        Long userId = authUser(authToken);
        Long userRaceId = super.getLastUserRaceId(userId);
        ChargeDiamondProtocal.ChargeDiamondReq chargeDiamondReq = reqWrapper.getChargeDiamondReq();
        int count = chargeDiamondReq.getDiamondCount();
        userExtService.chargeDiamond(userRaceId, count);
        RespWrapperProtocal.RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }
}
