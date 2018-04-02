/**
 * Project Name:baomq
 * File Name:BaoContext.java
 * Package Name:cn.bofowo.openmessaging.context
 * Date:2017年5月5日下午8:54:34
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.context;

import cn.bofowo.openmessaging.Queue;
import cn.bofowo.openmessaging.Topic;
import cn.bofowo.openmessaging.netty.NettyServerContext;
import io.openmessaging.BytesMessage;
import io.openmessaging.Message;
import io.openmessaging.Producer;

/**
 * ClassName:BaoContext <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午8:54:34 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface OmContext {
	public void loadTopic();
	public void loadQueue();
	/**
	 * 
	 * poll:消息消费通知. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author mqb
	 * @param message
	 * @return 
	 * @since JDK 1.7
	 */
	public Message poll(String queueName);
	/**
	 * 
	 * send:消息发送通知. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author mqb
	 * @param message
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	public void send(Message message) throws Exception;
}

