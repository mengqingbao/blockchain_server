/**
 * Project Name:baomq
 * File Name:QueueCreateListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月12日下午2:23:40
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import io.openmessaging.Message;
import io.openmessaging.demo.DefaultBytesMessage;
import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.Queue;
import cn.bofowo.openmessaging.Topic;
import cn.bofowo.openmessaging.context.OmContext;
import cn.bofowo.openmessaging.entity.IndexStorageFile;
import cn.bofowo.openmessaging.entity.MessageStorageFile;

/**
 * ClassName:QueueCreateListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月12日 下午2:23:40 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class QueueCreateListener implements OmListener {

	private DefaultBytesMessage message;
	@Override
	public void excute() {
		MessageStorageFile msf=new MessageStorageFile(ConfigConstant.MQ_MESSAGE_SEND+"/"+message.getQueue(), String.valueOf(message.getId()));
		msf.saveFile(message);
	}

	@Override
	public void setObject(Object obj) {
		message = (DefaultBytesMessage) obj;
		
		//MessageStorageFile.getInstance(ConfigConstant.STORE_PATH+"/"+message.getQueue(), String.valueOf(message.getId())).saveFile(message);
	}

	

}

