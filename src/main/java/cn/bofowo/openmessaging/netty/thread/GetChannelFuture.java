package cn.bofowo.openmessaging.netty.thread;

import java.util.concurrent.Callable;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;

public class GetChannelFuture implements Callable<Channel> {
	
	private ChannelFactory channelFactory;
	public GetChannelFuture(){
	this.channelFactory=channelFactory;	
	}

	@Override
	public Channel call() throws Exception {
		return channelFactory.newChannel();
	}

}
