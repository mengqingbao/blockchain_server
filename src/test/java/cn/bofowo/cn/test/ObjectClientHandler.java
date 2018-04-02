package cn.bofowo.cn.test;

import cn.bofowo.openmessaging.message.DefaultMessage;
import io.netty.channel.ChannelHandlerContext;
import io.openmessaging.Message;

public class ObjectClientHandler extends io.netty.channel.ChannelInboundHandlerAdapter {
	  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
       Message message=new DefaultMessage();
       message.setQueue("test");
        ctx.write(message);  
        ctx.flush();  
        System.out.println("message channelActive");  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	Message message=(DefaultMessage) msg;  
    	System.out.println(message.getQueue());
        System.out.println("channelRead");  
        //ctx.close();  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        cause.printStackTrace();  
        ctx.close();  
    }  
}
