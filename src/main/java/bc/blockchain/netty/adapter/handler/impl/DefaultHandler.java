package bc.blockchain.netty.adapter.handler.impl;

import io.netty.channel.Channel;
import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.AbstractHandler;
import bc.blockchain.server.BlockChainContext;

public class DefaultHandler extends AbstractHandler {
	public DefaultHandler(BlockChainContext blockChainContext) {
		this.blockChainContext=blockChainContext;
	}

	@Override
	public void process(Channel channel, Message messsageInfo) {
		
		channel.write("error code.");
		channel.close();
	}

}
