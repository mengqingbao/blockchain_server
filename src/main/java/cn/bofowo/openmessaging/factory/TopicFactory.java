/**
 * Project Name:baomq
 * File Name:DefaultTopicFactory.java
 * Package Name:cn.bofowo.openmessaging.factory
 * Date:2017年5月5日下午6:34:39
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.factory;

import java.io.File;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.DefaultTopic;
import cn.bofowo.openmessaging.Queue;
import cn.bofowo.openmessaging.Topic;

/**
 * ClassName:DefaultTopicFactory <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午6:34:39 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TopicFactory{

	
	public Topic createTopic(String name) {
		Topic topic=new DefaultTopic(name);
		this.checkDir(name);		
		return topic;
	}

	public Topic createTopic(String name,Queue queue) {
		Topic topic=new DefaultTopic(name,queue);
		return topic;
	}
	
	public void checkDir(String name){
		File file=new File(ConfigConstant.MQ_MESSAGE_PATH+"/"+name+"/index");
		if(!file.exists()){
			file.mkdirs();
		}
		file=new File(ConfigConstant.MQ_MESSAGE_PATH+"/"+name+"/data");
		if(!file.exists()){
			file.mkdirs();
		}
	}


}

