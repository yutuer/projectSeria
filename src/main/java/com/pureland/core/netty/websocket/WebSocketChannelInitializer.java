package com.pureland.core.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	public WebSocketChannelInitializer() throws ServletException {
		// 初始化spring容器
		new ClassPathXmlApplicationContext("spring/server_ctx.xml");
	}

	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast("httpCodec", new HttpServerCodec());
		pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
		pipeline.addLast("chunkWrite", new ChunkedWriteHandler());
		pipeline.addLast("uriCheck", new FirstHttpRequestHandler("/apis/reqWrapper"));
		pipeline.addLast("handshake", new WebSocketServerProtocolHandler("")); // websocket的handler部分定义的，它会自己处理握手等操作
		pipeline.addLast("gameLogic", new WebSocketFrameGameCodec()); // websocketFrame
																		// <->
																		// protobuf
																		// 编解码
		pipeline.addLast("handler", new WebSocketNoVerifyHandler());
	}

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = "com.pureland")
	static class WebConfig extends WebMvcConfigurerAdapter {
	}

}
