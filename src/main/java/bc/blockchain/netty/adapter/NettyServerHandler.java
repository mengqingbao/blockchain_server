package bc.blockchain.netty.adapter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import bc.blockchain.message.BcConstrant;
import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.factory.HandlerFactory;
import bc.blockchain.netty.adapter.handler.proxy.HandlerProxy;
import bc.blockchain.server.BlockChainContext;
import bc.blockchain.util.BeanJsonUtil;
import cn.bofowo.openmessaging.entity.TopicQueueSocketChannelMap;
import cn.bofowo.openmessaging.message.MessageType;
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
	private BlockChainContext blockChainContext;

	public NettyServerHandler(BlockChainContext blockChainContext) {
		this.blockChainContext = blockChainContext;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext cxt, String message) throws Exception {
		
		Channel channel=cxt.channel();
		Message messsageInfo=BeanJsonUtil.jsonToBean(message,Message.class);
		MessageType messageType=MessageType.getByCode(messsageInfo.getHeader().get(BcConstrant.HEADER_CODE).toString());
		messsageInfo.setMessageType(messageType);
		HandlerProxy proxy=HandlerFactory.getInstance(blockChainContext).createDefaultProxy();
		proxy.doHandle(channel,messsageInfo);
	}

	@Override
	public boolean acceptInboundMessage(Object msg) throws Exception {
		return super.acceptInboundMessage(msg);
	}

	
	public SocketChannel getSocketCannel(String key) {
		return TopicQueueSocketChannelMap.getInstance().get(key);
	}
}
