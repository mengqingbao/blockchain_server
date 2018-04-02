package bc.blockchain.netty.adapter.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

import bc.blockchain.message.Message;
import bc.blockchain.server.BlockChainContext;

public interface Handler {
	
	/**
	 * 处理逻辑
	 */
	public void process(Channel channel, Message messsageInfo);
	
	/**
	 * 构架handler链表
	 * @param handler
	 */
	public void setHandler(Handler handler);
	
	
	public Handler nextHandler();
	
	
	public void setChannel(Channel channel);
	
	public void setMessage(Message message);
	
	public void setBlockChainContext(BlockChainContext context);

}
