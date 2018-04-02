package bc.blockchain.netty.adapter;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.openmessaging.Message;

import java.net.InetSocketAddress;
import java.util.Date;

import bc.blockchain.peer.Peer;
import bc.blockchain.server.BlockChainContext;
import cn.bofowo.openmessaging.entity.TopicQueueSocketChannelMap;
import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.message.MessageType;
@Sharable
public class NettyServerAdapter extends ChannelInboundHandlerAdapter {
	private BlockChainContext blockChainContext;

	public NettyServerAdapter(BlockChainContext blockChainContext) {
		this.blockChainContext = blockChainContext;
	}


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Message message=(Message) msg;
		MessageType messageType=MessageType.getByCode(message.getType());
		switch (messageType) {
		case REG:
			 SocketChannel clientChannel=(SocketChannel) ctx.channel();
			 InetSocketAddress isd=clientChannel.localAddress();
			 Peer peer=createPeer(message.getType(),isd.getHostString(),isd.getPort());
			 message.setRespCode("200");
			 clientChannel.writeAndFlush(message);
			break;
		case DISTORY:
			
			break;
		case HEARTBEAT:
			
			break;
		case COMMON:
			
			break;
		case EXCEPTION:
			break;
		default:
			break;
		}
	}


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("++_____");
		if (evt instanceof IdleStateEvent) {  
			  
            IdleStateEvent event = (IdleStateEvent) evt;  
              
            if (event.state().equals(IdleState.READER_IDLE)) {  
                //未进行读操作  
                System.out.println("READER_IDLE");  
                // 超时关闭channel  
                 //ctx.close();  
  
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {  
                  
  
            } else if (event.state().equals(IdleState.ALL_IDLE)) {  
                // 发送心跳消息  
                DefaultMessage message=new DefaultMessage();
                message.setType(MessageType.HEARTBEAT.getType());
                ctx.channel().writeAndFlush(message);  
                  
            }  
  
        }  
		super.userEventTriggered(ctx, evt);
		
	}


	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server shutdown.");
		//nettyServerContext.removeChannelFromLiveMap(ctx.channel().id().asShortText());
		super.channelInactive(ctx);
	}


	public SocketChannel getSocketCannel(String key) {
		return TopicQueueSocketChannelMap.getInstance().get(key);
	}

	public void regChannel(String key, SocketChannel channel) {
	}

	public void attachTopic(String topic, String queue) {
	}

	private Peer createPeer(String custmId,String addr,Integer port){
		Peer peer=new Peer();
		peer.setAddress(addr);
		peer.setPort(port);
		peer.setLiveTime(new Date());
		return peer;
		
	}

}
