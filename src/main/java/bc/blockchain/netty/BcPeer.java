package bc.blockchain.netty;

import bc.blockchain.netty.adapter.NettyClientAdapter;
import io.netty.channel.Channel;
import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.netty.ServerChannelFactory;
import cn.bofowo.openmessaging.netty.uri.URI;

public class BcPeer {
	
	private NettyClientAdapter handler = null;
	private ServerChannelFactory channelFactory = null;
	private Channel channel;
	public BcPeer(){
		initContext();
	}
	public void initContext() {
		handler = new NettyClientAdapter(this);
		channelFactory = new ServerChannelFactory(handler);
		// this.initConnectChannel(urlMap);
	}

	public void createChannel(String ip, int port, String queueName)
			throws Exception {
		URI uri = new URI(ip, port);
		String key=ip+port;
		if(channel==null){
			channel = channelFactory.createChannel(uri);
		}
		registerClient(channel,queueName);
	}

	private void registerClient(Channel channel,String queueName) {
		DefaultMessage message =new DefaultMessage();
		message.setType("REG");
		message.setQueue(queueName);
		channel.writeAndFlush(message);
		
	}
}
