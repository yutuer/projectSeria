package com.pureland.core.handler.api;

import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.CompleteInfoReqProtocal.CompleteInfoReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.UserService;
import com.pureland.core.service.impl.UserServiceImpl;

/**
 * Created by qinpeirong on 15-1-15.
 */
public class BindAccountHandler extends RequestAPIHandler {
    private UserService userService = (UserService) SpringContextUtil.getBean(UserServiceImpl.class.getSimpleName());

    @Override
    public RespWrapper handler(ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);
        CompleteInfoReq completeInfoReq = reqWrapper.getCompleteInfoReq();
        String account = completeInfoReq.getAccount();
        String passwd = completeInfoReq.getPasswd();
        String phoneNum = completeInfoReq.getPhoneNum();
        userService.bindAcccount(userId, account, passwd, phoneNum);
        RespWrapper respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        PurelandLog.info(reqWrapper.toString());
        return respWrapper;
    }
}
