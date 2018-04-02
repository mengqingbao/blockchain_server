/**
 * Project Name:baomq
 * File Name:Client.java
 * Package Name:cn.bofowo.cn.test
 * Date:2017年6月3日下午12:03:02
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.cn.test;

import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.netty.decoder.NettyDecoder;
import cn.bofowo.openmessaging.netty.decoder.NettyEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.openmessaging.Message;

/**
 * ClassName:Client <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月3日 下午12:03:02 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Client {

	public static void main(String[] args) throws InterruptedException {  
        EventLoopGroup workerGroup = new NioEventLoopGroup(); 
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup)  
                    .channel(NioSocketChannel.class)  
                    .option(ChannelOption.SO_KEEPALIVE, true)  
                    .handler(new ChannelInitializer<SocketChannel>() {  
                        @Override  
                        protected void initChannel(SocketChannel ch) throws Exception {  
                        	  ChannelPipeline pipeline = ch.pipeline();  
                        	  pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
                              pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
                              pipeline.addLast("decoder", new NettyDecoder());  
                              pipeline.addLast("encoder", new NettyEncoder());  
                              pipeline.addLast(new ObjectClientHandler());    
                        }  
                    });  
            ChannelFuture f = b.connect("127.0.0.1", 6139).sync();  
            f.channel().closeFuture().sync();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
}