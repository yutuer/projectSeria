package com.pureland.core.netty.http;

import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BaseReqProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.handler.RequestAPIMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qinpeirong on 15-1-28.
 */
public class RequestWrapperHandler extends SimpleChannelInboundHandler<BaseReqProtocal.BaseReq> {
    private String TAG = PurelandLog.getClassTag(RequestWrapperHandler.class);
    private RequestAPIMap requestAPIMap = (RequestAPIMap) SpringContextUtil.getBean(RequestAPIMap.class.getSimpleName());

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, BaseReqProtocal.BaseReq baseReq) throws Exception {
        if (StringUtils.isNotEmpty(baseReq.toString())) {
            PurelandLog.info(TAG, baseReq.toString());
            String authToken = baseReq.getAuthToken();
            Integer sequenceId = baseReq.getSequenceId();
            Long timestamp = baseReq.getTimestamp();
            ReqWrapperProtocal.ReqWrapper reqWrapper = baseReq.getReqWrapper();
            ReqWrapperProtocal.ReqWrapper.RequestType requestType = reqWrapper.getRequestType();

            RequestAPIHandler handler = requestAPIMap.getApiHandlerMap().get(requestType.toString());
            RespWrapperProtocal.RespWrapper respWrapper = handler.handler(reqWrapper, authToken, timestamp);
            PurelandLog.info(TAG, respWrapper.toString());
            ctx.channel().writeAndFlush(respWrapper);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
