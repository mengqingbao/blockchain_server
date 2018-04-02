/**
 * Project Name:baomq
 * File Name:TopicListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月11日下午3:25:55
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import io.openmessaging.demo.DefaultBytesMessage;
import io.openmessaging.exception.OMSException;
import cn.bofowo.openmessaging.DefaultTopic;
import cn.bofowo.openmessaging.util.NetMockUtil;
import cn.bofowo.openmessaging.util.StringUtil;

/**
 * ClassName:TopicListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月11日 下午3:25:55 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TopicSendListener implements OmListener {

	private DefaultBytesMessage message;
	@Override
	public void excute() {
		
		if(StringUtil.isEmpty(message.getQueue())){
			//NetMockUtil.send(message.getQueue(), message.getId());
		}
		if(StringUtil.isEmpty(message.getTopic())){
			//NetMockUtil.sendTopic(message.getTopic(), message.getId());
		}

	}

	@Override
	public void setObject(Object object) {
			this.message=(DefaultBytesMessage) object;

	}

}

