/**
 * Project Name:baomq
 * File Name:DefaultQueueFactory.java
 * Package Name:cn.bofowo.openmessaging.factory
 * Date:2017年5月5日下午6:34:09
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.factory;

import java.io.File;

import io.openmessaging.Message;
import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.DefaultQueue;
import cn.bofowo.openmessaging.Queue;
import cn.bofowo.openmessaging.Topic;

/**
 * ClassName:DefaultQueueFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午6:34:09 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class QueueFactory{

	public Queue createQueue(String name) {
		Queue queue=new DefaultQueue(name);
		checkDir(name);
		return queue;
	}

	public Queue createQueue(String name,Topic topic) {
		Queue queue=new DefaultQueue(name,topic);
		return queue;
	}
	
	public void checkDir(String name){
		File file=new File(ConfigConstant.MQ_MESSAGE_SEND+"/"+name+"/index");
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(ConfigConstant.MQ_MESSAGE_SEND+"/"+name+"/data");
		if(!file.exists()){
			file.mkdirs();
		}
	}

}

