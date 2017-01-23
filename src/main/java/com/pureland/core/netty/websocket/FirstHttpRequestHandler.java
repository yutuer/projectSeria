package com.pureland.core.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * Created by Administrator on 2015/3/3.
 */
public class FirstHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private final String wsUri;

	public FirstHttpRequestHandler(String wsUri) {
		this.wsUri = wsUri;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if (wsUri.equalsIgnoreCase(request.uri())) {
			// 传递请求到管道的下一个handler,这里需要调用retain,因为在channelRead()完成之后会在FullHttpRequest上调用release()
			ctx.fireChannelRead(request.retain());
		} else {
			send100Continue(ctx);
		}
	}

	private static void send100Continue(ChannelHandlerContext ctx) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
		ctx.writeAndFlush(response);
	}

}
