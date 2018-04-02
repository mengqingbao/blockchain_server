/**
 * Project Name:baomq
 * File Name:TopicConstant.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月3日下午2:40:31
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;
/**
 * ClassName:TopicConstant <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月3日 下午2:40:31 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ConfigConstant {
	//保存文件路径
	public static String STORE_PATH="/Users/hanahyu/mengqingbao/alicode/test";
	//每个索引文件允许的最大消息数量
	public static Integer MAX_MQ_PER_FILE=5000;
	//允许最大的保存到消息数量
	public static Integer CAN_CACHE_MAX_NUM=1000000;
	//守护进程检查内存中的数据的间隔时间 默认时间5秒
	public static long DAEMON_MAX_CHECK_TIME=2000l;
		
	//消息的配置文件 请不要修改下方内容==========================================================================
	public static final String MQ_ID = "MQ_ID";
	public static String STORE_PATH_KEY="STORE_PATH";
	public static String MQ_STORAGE_KEY="MQ_STORAGE_FILE";
	public static String MQ_STORAGE_FILE="waiting.db";
	public static String MQ_CONSUMED_FILE_KEY="MQ_CONSUMED_RECORD_FILE";
	public static String MQ_CONSUMED_RECORD_FILE="consumed.db";
	//创建的message消息保存的文件地址。
	public static String MQ_MESSAGE_PATH=STORE_PATH+"/message";
	//已经被消费掉的消息
	public static String MQ_MESSAGE_CONSUMED=STORE_PATH+"/consumed";
	//发送中的消息
	public static String MQ_MESSAGE_SEND=STORE_PATH+"/send";
	
	public static String MQ_TOPIC_INDEX_FILE="topic.index";
	
	public static String MQ_QUEUE_INDEX_FILE="queue.index";
	
	//索引创建的操作类型
	public static class INDEX{
		public static final String ADD="add";
		public static final String REMOVE="remove";
	}
	
	//发送topic event 
	public static String SEND_TOPIC_EVENT="send_topic_event";
	//发送message 到queue event
	public static String SEND_QUEUE_EVENT="send_queue_event";
	//穿件消息事件
	public static String CREATE_MESSAGE_EVENT="create_message_event";
	//绑定queue到topic事件
	public static String ATTACH_QUEUE_TO_TOPIC_EVENT="attach_q_t_event";
	public static String CREATE_MESSAGE_TO_TOPIC_EVENT="create_topic_message_event";
	public static String CREATE_MESSAGE_TO_QUEUE_EVENT="create_queue_message_event";
	public static final String MQ_MESSAGE_POP_EVENT = "mq_message_pop_event";
	
	public static String POLL_EVENT="poll_event";
}

