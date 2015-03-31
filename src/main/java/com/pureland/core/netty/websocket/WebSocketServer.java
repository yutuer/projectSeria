package com.pureland.core.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2015/3/2.
 */
public class WebSocketServer {

	private final int port;

	public WebSocketServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		ServerBootstrap server = new ServerBootstrap();
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // 这个是用于serversocketchannel的eventloop
		EventLoopGroup workerGroup = new NioEventLoopGroup(); // 这个是用于处理accept到的channel
		try {
			server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).localAddress(port).childHandler(new WebSocketChannelInitializer());
			ChannelFuture f = server.bind(port).sync();
			ChannelFuture cf = f.channel().closeFuture();
			cf.sync(); // 相当于在这里阻塞，直到serverchannel关闭
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 9090;
		}
		new WebSocketServer(port).run();
	}
}
