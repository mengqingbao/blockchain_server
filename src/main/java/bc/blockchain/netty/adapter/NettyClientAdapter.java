package bc.blockchain.netty.adapter;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.openmessaging.Message;

import java.util.Set;

import bc.blockchain.netty.BcPeer;
import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.message.MessageType;
import cn.bofowo.openmessaging.netty.NettyClientContext;
import cn.bofowo.openmessaging.netty.ServerChannelFactory;
import cn.bofowo.openmessaging.netty.proxy.InoviceConsumerProxy;
import cn.bofowo.openmessaging.netty.uri.URI;
@Sharable
public class NettyClientAdapter extends ChannelInboundHandlerAdapter{
	
	private NettyClientContext context;
	
	public NettyClientAdapter(NettyClientContext context){
		this.context=context;
	}
	
	public NettyClientAdapter(BcPeer bcPeer) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg==null){
			super.channelReadComplete(ctx);
			return;
		}
		Message message =(Message) msg;
		MessageType messageType=MessageType.getByCode(message.getType());
		switch (messageType) {
		case HEARTBEAT:
			 // 发送心跳消息  
            DefaultMessage repayMessage=new DefaultMessage();
            repayMessage.setType(MessageType.HEARTBEAT.getType());
            ctx.channel().writeAndFlush(message);  
			break;
		case COMMON:
			doProcess(message);
			break;
		case EXCEPTION:
			
			break;
		default:
			break;
		}
		super.channelReadComplete(ctx);
	}

	public void doProcess(Message message){
		Set<InoviceConsumerProxy> proxySet=context.getInovkeObj(message.getQueue());
		for(InoviceConsumerProxy proxy:proxySet){
			proxy.setMessage(message);
			proxy.execute();
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		
		
		
		if (evt instanceof IdleStateEvent) {  
			  
            IdleStateEvent event = (IdleStateEvent) evt;  
              
            if (event.state().equals(IdleState.READER_IDLE)) {  
                //未进行读操作  
                System.out.println("READER_IDLE");  
                // 超时关闭channel  
                 //ctx.close();  
  
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {  
                  
  
            } else if (event.state().equals(IdleState.ALL_IDLE)) {  
                //未进行读写  
                System.out.println("ALL_IDLE");  
                // 发送心跳消息  
            }  
  
        }
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String channelKey=ctx.channel().id().asShortText();
		System.out.println("server shutdown "+channelKey);
		ServerChannelFactory factory=new ServerChannelFactory(this);
		URI uri=context.getChUriMap().get(channelKey);
		factory.createChannel(uri);
		super.channelInactive(ctx);
	}
	
	
	
	
}
