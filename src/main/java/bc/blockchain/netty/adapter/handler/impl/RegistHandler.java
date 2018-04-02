package bc.blockchain.netty.adapter.handler.impl;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.AbstractHandler;
import bc.blockchain.server.BlockChainContext;

public class RegistHandler extends AbstractHandler {
	
	public RegistHandler(BlockChainContext blockChainContext) {
		this.blockChainContext=blockChainContext;
	}

	@Override
	public void process(Channel channel, Message messsageInfos) {
		InetSocketAddress socket=(InetSocketAddress) channel;
		String ip = socket.getAddress().getHostAddress();
		Integer port=socket.getPort();
		System.out.print("ip:"+ip +"  port:"+port);
		channel.write("success");
		channel.close();
	
	}

}
