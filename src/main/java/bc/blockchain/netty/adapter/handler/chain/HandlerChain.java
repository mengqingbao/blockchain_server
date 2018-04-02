package bc.blockchain.netty.adapter.handler.chain;

import io.netty.channel.Channel;

import java.util.LinkedList;

import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.Handler;

public class HandlerChain {
	private LinkedList<Handler> link=new LinkedList();
	
	public void doHandle(Channel channel, Message messsageInfo){
		for(Handler handler:link){
			handler.process(channel, messsageInfo);
		}
	}
	public void addHandler(Handler handler){
		link.add(handler);
	}
}
