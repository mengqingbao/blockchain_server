package cn.bofowo.openmessaging.netty;

import java.util.concurrent.TimeUnit;

import bc.blockchain.netty.adapter.BaomqConnStatusHandler;
import bc.blockchain.netty.adapter.NettyClientAdapter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import cn.bofowo.openmessaging.netty.decoder.NettyDecoder;
import cn.bofowo.openmessaging.netty.decoder.NettyEncoder;
import cn.bofowo.openmessaging.netty.uri.URI;

public class ServerChannelFactory{

	private NettyClientAdapter handler;
	
	private ChannelFuture future;
	
	private NettyServerContext nettyServerContext;
	
	private ChannelFutureListener channelFutureListener = null;

	public ServerChannelFactory(NettyClientAdapter handler){
		this.handler=handler;
	}

	/**
	 * 创建 bootstrap对象
	 * @return
	 */
	private Bootstrap createBootstrap() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder", new NettyDecoder());
				pipeline.addLast("encoder", new NettyEncoder());
				//pipeline.addLast(new IdleStateHandler(3, 0, 0,TimeUnit.SECONDS));
				pipeline.addLast("handler", handler);
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		b.option(ChannelOption.TCP_NODELAY,true);
		b.option(ChannelOption.SO_TIMEOUT,5000);
		
		return b;
	}

	public Channel createChannel(URI uri) {
		Channel channel=null;
		Bootstrap bootstrap=this.createBootstrap();
		if(channelFutureListener==null){
			this.createListener(uri, bootstrap);
		}
		this.doConnect(uri, bootstrap);
		try {
			channel = future.sync().channel();
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		return channel;
	}
	
	
	private void createListener(URI uri,Bootstrap bootstrap){
		channelFutureListener = new ChannelFutureListener() {  
			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if (future.isSuccess()) {  
                      	System.out.println("连接成功 ping 消息成功");
                } else { 
                	
                    //  5秒后重新连接  
                	future.channel().eventLoop().schedule(new Runnable() {  
                        @Override  
                        public void run() {  
                        	System.out.println("测试连接 重连。");
                        	try{
                        	doConnect(uri,bootstrap);  
                        	}catch(Exception e){
                        		System.out.println("dddddd+++++++++++++++");
                        	}
                        }  
                    }, 5, TimeUnit.SECONDS);  
                }  
			}  
        };  
	}
	
	private void doConnect(URI uri,Bootstrap bootstrap){
		future=bootstrap.connect(uri.getIp(), uri.getPort());
		future.addListener(channelFutureListener);
	}
}
