/**
 * Project Name:baomq
 * File Name:Topic.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月5日下午4:34:47
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import io.openmessaging.Message;

import java.util.List;

/**
 * ClassName:Topic <br/>
 * Function: TODO topic定义为queue的set集合。每个topic至少有一个queue<br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午4:34:47 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface Topic {
	public List<Queue> getQueues();
	public void attachQueue(Queue queue);
	public void removeQueue(Queue queue);
	public void push(Message message);
	public Message pop();
}

