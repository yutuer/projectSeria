package com.pureland.core.handler.api;

import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.ShopBuyService;
import com.pureland.core.service.impl.ShopBuyServiceImpl;

/**
 * Created by Administrator on 2015/1/27.
 */
public class ShopBuyHandler extends RequestAPIHandler {

    private ShopBuyService shopBuyService = (ShopBuyService) SpringContextUtil.getBean(ShopBuyServiceImpl.class.getSimpleName());

    @Override
    public RespWrapperProtocal.RespWrapper handler(ReqWrapperProtocal.ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
        Long userId = authUser(authToken);


        return null;
    }
}
