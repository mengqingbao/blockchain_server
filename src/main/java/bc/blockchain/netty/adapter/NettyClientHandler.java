package bc.blockchain.netty.adapter;

import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.message.MessageType;
import cn.bofowo.openmessaging.netty.NettyClientContext;
import cn.bofowo.openmessaging.netty.proxy.InoviceConsumerProxy;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.openmessaging.Message;
@Sharable
public class NettyClientHandler extends SimpleChannelInboundHandler{

	private NettyClientContext context;
	
	public NettyClientHandler(NettyClientContext nettyClientContext) {
		this.context=nettyClientContext;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg==null){
			return;
		}
		Message message =(Message) msg;
		MessageType messageType=MessageType.getByCode(message.getType());
		switch (messageType) {
		case HEARTBEAT:
			break;
		case COMMON:
			InoviceConsumerProxy proxy =(InoviceConsumerProxy) context.getInovkeObj(message.getQueue());
			proxy.execute();
			break;
		case EXCEPTION:
			break;
		default:
			break;
		}
		
	}
}
