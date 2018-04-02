/**
 * Project Name:baomq
 * File Name:TopicCreateListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月12日下午2:24:07
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.entity.MessageStorageFile;
import cn.bofowo.openmessaging.util.FileSearchUtil;
import io.openmessaging.demo.DefaultBytesMessage;

/**
 * ClassName:TopicCreateListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月12日 下午2:24:07 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TopicCreateListener implements OmListener {

	private DefaultBytesMessage message;
	@Override
	public void excute() {
		MessageStorageFile msf=new MessageStorageFile(ConfigConstant.MQ_MESSAGE_PATH+"/"+message.getTopic(), String.valueOf(message.getId()));
		//msf.saveFile(message);
	}

	@Override
	public void setObject(Object obj) {
		message=(DefaultBytesMessage) obj;
	}

}

