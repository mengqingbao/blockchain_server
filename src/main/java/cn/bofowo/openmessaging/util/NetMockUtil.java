/**
 * Project Name:baomq
 * File Name:NetUtil.java
 * Package Name:cn.bofowo.openmessaging.util
 * Date:2017年5月19日下午9:11:09
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.util;

import io.openmessaging.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.entity.MessageStorageFile;
import cn.bofowo.openmessaging.properties.BaoPropertiesUtil;

/**
 * ClassName:NetUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月19日 下午9:11:09 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class NetMockUtil {

	
	public static Map<String, LinkedList<String>> queueBackets = new HashMap<>();
	
	public static Map<String,LinkedList<String>> topicBuckets=new HashMap<>();
	
	/**
	 * 通知消费端可以消费信息
	 *
	 * @author mqb
	 * @param queueName
	 * @param name
	 * @since JDK 1.7
	 */
	public static void send(String queueName,String name){
		LinkedList<String> queue=queueBackets.get(queueName);
		if(queue==null){
			queue=new LinkedList<String>();
			
		}
		queue.push(name);
		queueBackets.put(queueName, queue);
	}
	
	public static void sendTopic(String topic,String name){
		LinkedList<String> queue=queueBackets.get(topic);
		if(queue==null){
			queue=new LinkedList<String>();
			
		}
		queue.push(name);
		topicBuckets.put(topic, queue);
	}
	
	/**
	 * 通知发送端已经消费掉
	 * 
	 * 
	 * notify:
	 *
	 * @author mqb
	 * @param queueName
	 * @param name
	 * @since JDK 1.7
	 */
	public static void notify(String queueName,String name){
		LinkedList<String> queue=queueBackets.get(queueName);
		if(queue==null){
			queue=new LinkedList<String>();
			
		}
		queue.push(name);
		queueBackets.put(queueName, queue);
	}

	//网络拉取消息
	public static Message pull(String queueName) {
		
		/*LinkedList<String> queue=queueBackets.get(queueName);
		if(queue==null){
			return null;
		}
		String name=queue.getFirst();*/
		String name="";
		if(StringUtil.isEmpty(name)){
			name=String.valueOf(BaoPropertiesUtil.readMinValue(queueName+"_min"));
		}
		if("0".equals(name)){
			return null;
		}
		MessageStorageFile msf=new MessageStorageFile(ConfigConstant.MQ_MESSAGE_SEND+"/"+queueName, String.valueOf(name));
		Message message=msf.readFile();
		
		queueBackets.get(queueName).remove(name);
		msf.delete();
		return message;
	}
}

