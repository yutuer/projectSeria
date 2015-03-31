package com.pureland.core.netty.websocket;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.pureland.common.protocal.BaseReqProtocal;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by Administrator on 2015/3/2.
 */
public abstract class WebSocketHandler extends SimpleChannelInboundHandler<BaseReqProtocal.BaseReq> {

    protected RequestAPIMap requestAPIMap = (RequestAPIMap) SpringContextUtil.getBean(RequestAPIMap.class.getSimpleName());

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //握手完成
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            //移除HTTP请求处理器
            ctx.pipeline().remove(FirstHttpRequestHandler.class);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelGroupUtil.removeFromPid2ChannelMaps(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelGroupUtil.addChannel2Global(ctx.channel());
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

	public void sendError2Client(final ChannelHandlerContext ctx, BaseRespProtocal.BaseResp.ErrorType errorType, Exception e) {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream s = new PrintStream(baos);
		e.printStackTrace(s);
        BaseRespProtocal.BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(errorType).setErrorMessage(baos.toString()).build();
        ctx.channel().writeAndFlush(baseResp);
    }

    public void sendSuccess2Client(final ChannelHandlerContext ctx, RespWrapperProtocal.RespWrapper respWrapper) {
        if (respWrapper == null) {
            respWrapper = RespWrapperProtocal.RespWrapper.newBuilder().build();
        }
        BaseRespProtocal.BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(BaseRespProtocal.BaseResp.ErrorType.OK).setRespWrapper(respWrapper).build();
        ctx.channel().writeAndFlush(baseResp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}