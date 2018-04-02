/**
 * Project Name:baomq
 * File Name:QueueListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月11日下午3:26:21
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import io.openmessaging.demo.DefaultBytesMessage;
import cn.bofowo.openmessaging.DefaultTopic;
import cn.bofowo.openmessaging.Topic;
import cn.bofowo.openmessaging.netty.NettyServerContext;
import cn.bofowo.openmessaging.util.NetMockUtil;

/**
 * ClassName:QueueListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月11日 下午3:26:21 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class QueueSendListener implements OmListener {

	private DefaultBytesMessage message;
	
	private NettyServerContext server;
	public QueueSendListener(NettyServerContext server) {
		this.server=server;
	}

	@Override
	public void excute() {
		//NetMockUtil.send(message.getQueue(), String.valueOf(message.getId()));
		
	}

	@Override
	public void setObject(Object object) {
		message = (DefaultBytesMessage) object;
	}

}

