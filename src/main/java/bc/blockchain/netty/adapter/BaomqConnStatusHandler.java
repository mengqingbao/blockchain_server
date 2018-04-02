package bc.blockchain.netty.adapter;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.openmessaging.Message;

public class BaomqConnStatusHandler extends IdleStateHandler {

	public BaomqConnStatusHandler(boolean observeOutput, long readerIdleTime, long writerIdleTime, long allIdleTime,
			TimeUnit unit) {
		super(observeOutput, readerIdleTime, writerIdleTime, allIdleTime, unit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
		 if (evt instanceof IdleStateEvent) {  
			  
	            IdleStateEvent event = (IdleStateEvent) evt;  
	              
	            if (event.state().equals(IdleState.READER_IDLE)) {  
	                //未进行读操作  
	                System.out.println("READER_IDLE");  
	                // 超时关闭channel  
	                 ctx.close();  
	  
	            } else if (event.state().equals(IdleState.WRITER_IDLE)) {  
	                  
	  
	            } else if (event.state().equals(IdleState.ALL_IDLE)) {  
	                //未进行读写  
	                System.out.println("ALL_IDLE");  
	                // 发送心跳消息  
	               
	                  
	            }  
	}
	
	
	}

}
