package cn.bofowo.openmessaging.netty.listener;

import java.util.concurrent.TimeUnit;

import cn.bofowo.openmessaging.netty.uri.URI;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class BaomqFutureListener implements ChannelFutureListener {

	private ChannelFuture future;
	private Bootstrap bootstrap;
	public  BaomqFutureListener( ChannelFuture future,Bootstrap bootstrap){
		this.future=future;
		this.bootstrap=bootstrap;
	}
	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		if (future.isSuccess()) {  
            
        } else { 
            //  5秒后重新连接  
        	future.channel().eventLoop().schedule(new Runnable() {  
                @Override  
                public void run() {  
                	System.out.println("测试连接 重连。");
                }  
            }, 5, TimeUnit.SECONDS);  
        }  

	}

}
