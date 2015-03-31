package com.pureland.core.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;

/**
* Created by qinpeirong on 15-1-28.
*/
public class DispatcherServletChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final DispatcherServlet dispatcherServlet;

    public DispatcherServletChannelInitializer() throws ServletException {

        MockServletContext servletContext = new MockServletContext();

//        servletContext.addInitParameter(Log4jWebConfigurer.CONFIG_LOCATION_PARAM, "WEB-INF/log4j.properties");
//        ServletContextListener listener = new Log4jConfigListener();
//        ServletContextEvent event = new ServletContextEvent(servletContext);
//        listener.contextInitialized(event);



        MockServletConfig servletConfig = new MockServletConfig(servletContext);

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/spring/server_ctx.xml");

        AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
        wac.setParent(context);
        wac.setServletConfig(servletConfig);
        wac.register(WebConfig.class);
        wac.refresh();

        this.dispatcherServlet = new DispatcherServlet(wac);
        this.dispatcherServlet.init(servletConfig);
    }

    @Override
    public void initChannel(SocketChannel channel) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = channel.pipeline();

        // Uncomment the following line if you want HTTPS
        //SSLEngine engine = SecureChatSslContextFactory.getServerContext().createSSLEngine();
        //engine.setUseClientMode(false);
        //pipeline.addLast("ssl", new SslHandler(engine));


        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());

//        ExtensionRegistry registry = ExtensionRegistry.newInstance();
//        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
//        pipeline.addLast("protobufDecoder",
//                new ProtobufDecoder(BaseReqProtocal.BaseReq.getDefaultInstance(),
//                        registry));
//        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
//        pipeline.addLast("protobufEncoder", new ProtobufEncoder());

        pipeline.addLast("handler", new ServletNettyHandler(this.dispatcherServlet));
//        pipeline.addLast("handler", new RequestWrapperHandler());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ((ByteBuf)msg).release();
    }

    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages="com.pureland")
    static class WebConfig extends WebMvcConfigurerAdapter {
    }

}
