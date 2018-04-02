package bc.blockchain.netty.adapter.handler.proxy;

import io.netty.channel.Channel;
import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.chain.HandlerChain;

public class HandlerProxy {
	private HandlerChain handler;
	public HandlerProxy(HandlerChain handler){
		this.handler=handler;
	}
	
	public void doHandle(Channel channel, Message messsageInfo){
			handler.doHandle(channel, messsageInfo);
	}
	

}
