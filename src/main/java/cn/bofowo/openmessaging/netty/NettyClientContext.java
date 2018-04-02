package cn.bofowo.openmessaging.netty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bc.blockchain.netty.adapter.NettyClientAdapter;
import cn.bofowo.openmessaging.message.DefaultMessage;
import cn.bofowo.openmessaging.netty.proxy.InoviceConsumerProxy;
import cn.bofowo.openmessaging.netty.uri.URI;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
@Sharable
public class NettyClientContext {
	private List<URI> commonUriList = new ArrayList<URI>();
	private static Map<String, List<URI>> urlMap = new HashMap<String, List<URI>>();
	private static Map<String, Set<InoviceConsumerProxy>> inovkeObjeMap = new HashMap<String,Set<InoviceConsumerProxy>>();
	private static Map<String,Channel> serverSocketMap=new HashMap<String, Channel>();
	private static Map<String,Date> clientKeepLiveMap=new HashMap<String, Date>();
	private static Map<String,URI> chUriMap=new HashMap<String,URI>();
	private NettyClientAdapter handler = null;
	private ServerChannelFactory channelFactory = null;
	public NettyClientContext(){
		initContext();
	}
	public void initContext() {
		handler = new NettyClientAdapter(this);
		channelFactory = new ServerChannelFactory(handler);
		// this.initConnectChannel(urlMap);
	}

	public void createChannel(String ip, int port, String queueName)
			throws Exception {
		List<URI> uriList = urlMap.get(queueName);
		if (uriList == null) {
			uriList = new ArrayList<URI>();
		}
		URI uri = new URI(ip, port);
		String key=ip+port;
		Channel channel = serverSocketMap.get(key);
		if(channel==null){
			channel = channelFactory.createChannel(uri);
			serverSocketMap.put(key, channel);
			chUriMap.put(channel.id().asShortText(), uri);
		}
		registerClient(channel,queueName);
		uri.setChannel(channel);
		uriList.add(uri);
		urlMap.put(queueName, uriList);
	}

	private void registerClient(Channel channel,String queueName) {
		DefaultMessage message =new DefaultMessage();
		message.setType("REG");
		message.setQueue(queueName);
		channel.writeAndFlush(message);
		
	}
	public void addCommonUri(URI uri) {
		commonUriList.add(uri);
	}

	public void setCommonUriList(List<URI> commonUriList) {
		this.commonUriList = commonUriList;
	}

	public Set<InoviceConsumerProxy> getInovkeObj(String queueName) {
		return inovkeObjeMap.get(queueName);
	}

	public Map<String, Set<InoviceConsumerProxy>> getInvokeObj() {
		return inovkeObjeMap;
	}

	public List<URI> getCommonUriList() {
		return commonUriList;
	}
	public void registerProxy(String queue, InoviceConsumerProxy proxy) {
		Set<InoviceConsumerProxy> set=inovkeObjeMap.get(queue);
		if(set==null){
			set=new HashSet<>();
		}
		if(!set.contains(proxy)){
			set.add(proxy);
		}
		inovkeObjeMap.put(queue, set);
	}
	
	public void refreshServerAlive(String key){
		clientKeepLiveMap.put(key, new Date());
	}
	public ServerChannelFactory getChannelFactory() {
		return channelFactory;
	}
	public Map<String, URI> getChUriMap() {
		return chUriMap;
	}

}
