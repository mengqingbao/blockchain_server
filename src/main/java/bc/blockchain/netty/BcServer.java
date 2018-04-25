package bc.blockchain.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import bc.blockchain.callback.CallBack;
import bc.blockchain.netty.adapter.NettyServerAdapter;
import bc.blockchain.netty.decoder.NettyDecoder;
import bc.blockchain.netty.decoder.NettyEncoder;
import bc.blockchain.server.BlockChainContext;

public class BcServer {


	private BlockChainContext blockChainContext;
	
	private NettyServerAdapter nettyServerAdapter;
	/**
	 * 用于分配处理业务线程的线程组个数
	 */
	protected final int BIZGROUPSIZE = 100; // 默认

	/**
	 * 业务出现线程大小
	 */
	protected final int BIZTHREADSIZE = 4;
	
	private Integer port=12188;
	private String ip="0.0.0.0";

	/**
	 * NioEventLoopGroup实际上就是个线程池,
	 * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
	 * 每一个NioEventLoop负责处理m个Channel,
	 * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
	 */
	private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	public BcServer(BlockChainContext blockChainContext) {
		this.blockChainContext=blockChainContext;
		nettyServerAdapter = new NettyServerAdapter(blockChainContext);
	}


	public void run() throws Exception {

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup);
		b.channel(NioServerSocketChannel.class);
		b.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder", new NettyDecoder());
				pipeline.addLast("encoder", new NettyEncoder());
				pipeline.addLast(new IdleStateHandler(0, 0, 3,TimeUnit.SECONDS));
				pipeline.addLast(nettyServerAdapter);
			}
		});

		ChannelFuture f = b.bind(ip, port).sync();
		System.out.println("netty server start success...");

	}

	protected void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	
}
