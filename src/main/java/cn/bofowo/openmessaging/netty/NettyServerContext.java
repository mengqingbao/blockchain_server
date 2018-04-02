package cn.bofowo.openmessaging.netty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import bc.blockchain.netty.adapter.NettyServerAdapter;
import bc.blockchain.netty.adapter.NettyServerHandler;
import cn.bofowo.openmessaging.netty.decoder.NettyDecoder;
import cn.bofowo.openmessaging.netty.decoder.NettyEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.openmessaging.Message;

@Sharable
public class NettyServerContext {

	private static Map<String, List<SocketChannel>> queueMap = new ConcurrentHashMap();

	private static Map<String, List<String>> topicMap = new ConcurrentHashMap();
	
	private static Map<String,Date> clientKeepLiveMap=new HashMap<String, Date>();
	
	private NettyServerAdapter nettyServerAdapter;
	/**
	 * 用于分配处理业务线程的线程组个数
	 */
	protected final int BIZGROUPSIZE = 100; // 默认

	/**
	 * 业务出现线程大小
	 */
	protected final int BIZTHREADSIZE = 4;
	
	private Integer port;
	private String ip;

	/**
	 * NioEventLoopGroup实际上就是个线程池,
	 * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
	 * 每一个NioEventLoop负责处理m个Channel,
	 * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
	 */
	private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	public NettyServerContext() {
		nettyServerAdapter = new NettyServerAdapter(null);
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

		/**
		 * wait until the socket close
		 */
		f.channel().closeFuture().sync();

		shutdown();

	}

	protected void shutdown() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	public static void main(String[] args) throws Exception {
		new NettyServerContext().run();
	}

	public Map<String, List<SocketChannel>> getQueueMap() {
		return queueMap;
	}

	public void setQueueMap(Map<String, List<SocketChannel>> queueMap) {
		this.queueMap = queueMap;
	}

	public Map<String, List<String>> getTopicMap() {
		return topicMap;
	}

	public void setTopicMap(Map<String, List<String>> topicMap) {
		this.topicMap = topicMap;
	}

	public void registQueue(String queueName, SocketChannel channel) {
		List<SocketChannel> channelList = queueMap.get(queueName);
		if (channelList == null) {
			channelList = new ArrayList<SocketChannel>();
		}
		if (!channelList.contains(channel)) {
			channelList.add(channel);
			queueMap.put(queueName, channelList);
		}
		
	}

	public void attachQueueToTopic(String topic, String queue) {
		List<String> queueList = topicMap.get(topic);
		if (queueList == null) {
			queueList = new ArrayList<String>();
		}
		queueList.add(queue);
		topicMap.put(topic, queueList);
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void refreshClientAlive(String key){
		clientKeepLiveMap.put(key, new Date());
	}

	public static Map<String, Date> getClientKeepLiveMap() {
		return clientKeepLiveMap;
	}

	public List<SocketChannel> getSocketChannelsByQueue(String queueName) {
		return this.queueMap.get(queueName);
	}

	public boolean checkChannelLive(String channelId) {
		Date date=clientKeepLiveMap.get(channelId);
		if(date==null){
			return false;
		}
		if(date.getTime()> System.currentTimeMillis()-5000){
			return true;
		}
		return false;
	}

	public void removeChannelFromLiveMap(String asShortText) {
		clientKeepLiveMap.remove(asShortText);
	}
	
	public void putQueueMap(String queueName,List<SocketChannel> channels){
		queueMap.put(queueName, channels);
	}

}
