/**
 * Project Name:baomq
 * File Name:TopicQueueSocketChannelMap.java
 * Package Name:cn.bofowo.openmessaging.entity
 * Date:2017年6月26日上午12:44:20
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.entity;

import io.netty.channel.socket.SocketChannel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.bofowo.openmessaging.util.StringUtil;

/**
 * ClassName:TopicQueueSocketChannelMap <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月26日 上午12:44:20 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TopicQueueSocketChannelMap {
	
	public static TopicQueueSocketChannelMap map=null;
	public static TopicQueueSocketChannelMap getInstance(){
		if(map==null){
			init();
		}
		return map;
	}
	private static void init() {
		map=new TopicQueueSocketChannelMap();
	}
	
	private Map<String,List<String>> topicMap=new ConcurrentHashMap();
	
	private Map<String,SocketChannel> queueMap=new ConcurrentHashMap();
	
	
	public void register(String topicName,String queueName,SocketChannel channel) throws Exception{
		
		if(StringUtil.isEmpty(queueName)){
			throw new Exception("queue name couldn't be null.");
		}
		if(channel==null){
			throw new Exception("Can't register channel null");
		}
		if(StringUtil.isNotEmpty(topicName)){
			if(topicMap.containsKey(topicName)){
				List<String> temp=topicMap.get(topicName);
				temp.add(queueName);
				topicMap.put(topicName, temp);
			}
		}
		
		queueMap.put(queueName, channel);
	}
	public SocketChannel get(String key) {
		
		return queueMap.get(key);
	}
	
}

