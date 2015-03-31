package com.pureland.core.netty.http;

import com.pureland.common.log.PurelandLog;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by qinpeirong on 15-1-28.
 */
public class MyServer {
//    private String TAG = PurelandLog.getClassTag(MyServer.class);
//
//    private final int port;
//
//    public MyServer(int port) {
//        this.port = port;
//    }
//
//    public void run() throws Exception {
////        String rootPath = this.getClass().getProtectionDomain().getCodeSource()
////                .getLocation().getPath();
////        System.setProperty("testLog", rootPath);
//        ServerBootstrap server = new ServerBootstrap();
//        EventLoopGroup bossGroup = new NioEventLoopGroup();   //这个是用于serversocketchannel的eventloop
//        EventLoopGroup workerGroup = new NioEventLoopGroup();    //这个是用于处理accept到的channel
//        try {
//            server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
//                    .localAddress(port)
//                    .childHandler(new DispatcherServletChannelInitializer());
//
//            server.bind().sync().channel().closeFuture().sync();
//        } finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        int port;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        } else {
//            port = 8080;
//        }
//        new MyServer(port).run();
//    }
}

