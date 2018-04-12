package bc.blockchain.netty;

import io.netty.channel.Channel;

public class BcPeer {
	
	private Channel channel;
	public BcPeer(){
		initContext();
	}
	public void initContext() {
		// this.initConnectChannel(urlMap);
	}

	public void createChannel(String ip, int port, String queueName)
			throws Exception {
	}

	private void registerClient(Channel channel,String queueName) {
		System.out.println("注册信息");
	}
}
