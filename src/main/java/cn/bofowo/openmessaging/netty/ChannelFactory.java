package cn.bofowo.openmessaging.netty;

import bc.blockchain.netty.adapter.NettyClientHandler;
import cn.bofowo.openmessaging.netty.decoder.NettyDecoder;
import cn.bofowo.openmessaging.netty.decoder.NettyEncoder;
import cn.bofowo.openmessaging.netty.uri.URI;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.openmessaging.Message;

public class ChannelFactory{

	private NettyClientHandler handler;

	public ChannelFactory(NettyClientHandler handler){
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
				pipeline.addLast("handler", handler);
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public Channel getChannel(URI uri) {
		Bootstrap bootstrap=this.createBootstrap();
		Channel channel=null;
		try {
			channel = bootstrap.connect(uri.getIp(), uri.getPort()).sync().channel();
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
		return channel;
	}
}
