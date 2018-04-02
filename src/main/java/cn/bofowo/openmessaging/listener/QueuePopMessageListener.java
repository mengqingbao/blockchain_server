/**
 * Project Name:baomq
 * File Name:QueuePopMessageListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月12日下午2:48:55
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.context.OmApplicationContext;
import cn.bofowo.openmessaging.entity.MessageStorageFile;
import cn.bofowo.openmessaging.util.NetMockUtil;
import io.openmessaging.demo.DefaultBytesMessage;

/**
 * ClassName:QueuePopMessageListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月12日 下午2:48:55 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class QueuePopMessageListener implements OmListener {

	private DefaultBytesMessage message;
	private OmApplicationContext context;
	@Override
	public void excute() {
		System.out.println("消费消息同时将文件删除"+message.getTimestamp());
		MessageStorageFile msf=new MessageStorageFile(ConfigConstant.MQ_MESSAGE_PATH+"/"+message.getQueue(), String.valueOf(message.getId()));
		msf.delete();
		NetMockUtil.notify(message.getQueue(),String.valueOf(message.getTimestamp()));
	}

	@Override
	public void setObject(Object obj) {
		message = (DefaultBytesMessage) obj;
	}

	public void setContext(OmApplicationContext context) {
		this.context = context;
	}
	
	

}

