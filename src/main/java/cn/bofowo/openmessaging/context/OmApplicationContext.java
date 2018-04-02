/**
 * Project Name:baomq
 * File Name:BplicationContext.java
 * Package Name:cn.bofowo.openmessaging.context
 * Date:2017年5月5日下午8:36:59
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
 */

package cn.bofowo.openmessaging.context;

import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.MessageFactory;
import io.openmessaging.MessageHeader;
import io.openmessaging.Producer;
import io.openmessaging.demo.ClientOMSException;
import io.openmessaging.demo.DefaultBytesMessage;
import io.openmessaging.demo.DefaultMessageFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.Queue;
import cn.bofowo.openmessaging.Topic;
import cn.bofowo.openmessaging.entity.TopicQueueSocketChannelMap;
import cn.bofowo.openmessaging.factory.QueueFactory;
import cn.bofowo.openmessaging.factory.TopicFactory;
import cn.bofowo.openmessaging.factory.QueueFactory;
import cn.bofowo.openmessaging.factory.TopicFactory;
import cn.bofowo.openmessaging.listener.OmListener;
import cn.bofowo.openmessaging.netty.NettyServerContext;
import cn.bofowo.openmessaging.properties.BaoPropertiesUtil;
import cn.bofowo.openmessaging.util.FileSearchUtil;

/**
 * ClassName:BplicationContext <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月5日 下午8:36:59 <br/>
 * 
 * @author mqb
 * @version
 * @since JDK 1.7
 * @see
 */
public class OmApplicationContext implements 
		OmContext {

	private static Map<String, Topic> topicMap = new HashMap<String, Topic>();
	private static Map<String, Queue> queueMap = new HashMap<String, Queue>();
	private static TopicFactory topicFactory;
	private static QueueFactory queueFactory;
	private MessageFactory messageFactory;
	private static OmApplicationContext omAppliaction;
	private OmApplicationContextSupport contextSupport;
	private static NettyServerContext server;
	private  TopicQueueSocketChannelMap map;
	

	public static OmApplicationContext getInstance() {
		if (omAppliaction == null) {
			omAppliaction = new OmApplicationContext();
			omAppliaction.checkEnvironment(); // 环境准备
			omAppliaction.loadQueue();
			omAppliaction.loadQueue();
			topicFactory = new TopicFactory();
			queueFactory = new QueueFactory();
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO ("任务退出将数据持久化到硬盘");
				}
			}));
			if(server==null){
				server=new NettyServerContext();
			}
			// 启动守护进程
			omAppliaction.startDaemonThread();
			
		}
		
		return omAppliaction;
	}
	protected OmApplicationContext(){
		contextSupport=new OmApplicationContextSupport(this);
		messageFactory=new DefaultMessageFactory();
		map=new TopicQueueSocketChannelMap();
	}

	private void checkEnvironment() {
		File dir = new File(ConfigConstant.MQ_MESSAGE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		dir = new File(ConfigConstant.MQ_MESSAGE_SEND);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		dir = new File(ConfigConstant.MQ_MESSAGE_CONSUMED);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	@Override
	public void loadTopic() {
		List<String> topicDirNames = FileSearchUtil
				.sortFileName(ConfigConstant.MQ_MESSAGE_PATH);

	}

	@Override
	public void loadQueue() {
		List<String> queueDirNames = FileSearchUtil
				.sortFileName(ConfigConstant.MQ_MESSAGE_SEND);

	}

	@Override
	public Message poll(String queueName) {
		this.fireEvent(ConfigConstant.POLL_EVENT,queueName);
		
		return null;
	}

	@Override
	public void send(Message message) throws Exception {
		if (message == null)
			throw new ClientOMSException("Message should not be null");
		String topic = message.headers().getString(MessageHeader.TOPIC);
		String queue = message.headers().getString(MessageHeader.QUEUE);
		if ((topic == null && queue == null)
				|| (topic != null && queue != null)) {
			throw new ClientOMSException(String.format(
					"Queue:%s Topic:%s should put one and only one", true,
					queue));
		}
		if (topic != null) {
			
			this.fireEvent(ConfigConstant.SEND_TOPIC_EVENT,message);
		}
		if (queue != null) {
			
			this.fireEvent(ConfigConstant.SEND_QUEUE_EVENT,message);
		}
		
	}

	public void addListener(String event,OmListener listener) {
		contextSupport.addListener(event, listener);
	}

	public void fireEvent(String event,Object obj) {
		contextSupport.fireEvent(event, obj);
	}

	public void startDaemonThread() {
		Thread daemonThread = new Thread(new StartServer());
		daemonThread.setDaemon(true);
		daemonThread.start();
	}

	class StartServer implements Runnable {
		public void run() {
			try {
				server.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public NettyServerContext getServer() {
		return server;
	}

}
