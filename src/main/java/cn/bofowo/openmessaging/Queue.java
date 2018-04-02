/**
 * Project Name:baomq
 * File Name:Queue.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月5日下午4:35:01
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import io.openmessaging.Message;

/**
 * ClassName:Queue <br/>
 * Function: TODO queue 不需要依赖topic存在，可以独立存在。这样符合open-messaging思想<br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午4:35:01 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface Queue {
	public String getName();
	public void setTopic(Topic topic);
	public Topic getTopic();
	public void push(Message message);
	public Message pop();
}

