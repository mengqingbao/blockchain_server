package cn.bofowo.openmessaging.netty.proxy;

import java.util.ArrayList;
import java.util.List;

import cn.bofowo.openmessaging.context.OmApplicationContext;
import cn.bofowo.openmessaging.netty.NettyServerContext;
import cn.bofowo.openmessaging.util.StringUtil;
import io.netty.channel.socket.SocketChannel;
import io.openmessaging.Message;

public class ServerProxy {

	private static ServerProxy serverProxy;
	private NettyServerContext context;
	public ServerProxy(NettyServerContext context){
		this.context=context;
	}
	public static ServerProxy instance(){
		if(serverProxy==null){
			serverProxy=new ServerProxy(new NettyServerContext());
		}
		return serverProxy;
	}
	
	public void send(Message message){
			String topic=message.getTopic();
			if(topic!=null&&topic!=""){
				List<String> queues = context.getTopicMap().get(topic);
				for(String queueName:queues){
					List<SocketChannel> newScs=new ArrayList<>();
					List<SocketChannel> scs=context.getSocketChannelsByQueue(queueName);
					for(SocketChannel sc:scs){
						if(context.checkChannelLive(sc.id().asShortText())){
							sc.writeAndFlush(message);
							newScs.add(sc);
						}
					}
					context.putQueueMap(queueName, newScs);
				}
			}
			String queue=message.getQueue();
			if(StringUtil.isNotEmpty(queue)){
				 List<SocketChannel> scs=context.getQueueMap().get(queue);
				 if(scs==null){
					 return;
				 }
				 for(SocketChannel sc:scs){
						sc.writeAndFlush(message);
				}
			}
	}
}
