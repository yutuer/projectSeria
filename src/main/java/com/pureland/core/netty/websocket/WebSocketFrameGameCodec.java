package com.pureland.core.netty.websocket;

import com.pureland.common.protocal.BaseReqProtocal;
import com.pureland.common.protocal.BaseRespProtocal;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * Created by Administrator on 2015/3/19.
 */
public class WebSocketFrameGameCodec extends MessageToMessageCodec<WebSocketFrame, BaseRespProtocal.BaseResp> {

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List out) throws Exception {
        ByteBuf buf = msg.content();  //真正的数据是放在buf里面的
        if (buf.isReadable()) {
            byte[] contentByte = new byte[buf.readableBytes()];
            buf.readBytes(contentByte);
            BaseReqProtocal.BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(contentByte);
            out.add(baseReq);
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, BaseRespProtocal.BaseResp msg, List out) throws Exception {
        byte[] bb = msg.toByteArray();
        ByteBuf retBuf = Unpooled.copiedBuffer(bb);
        out.add(new BinaryWebSocketFrame(retBuf));
    }
}
