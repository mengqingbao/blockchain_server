/**
 * Project Name:baomq
 * File Name:DefaultTopic.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月5日下午4:47:17
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import io.openmessaging.Message;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName:DefaultTopic <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午4:47:17 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DefaultTopic implements Topic {

	private String name;
	private File[] indexFiles;
	
	private Map<String,Queue> queuesMap=new HashMap<String,Queue>();
	private  LinkedList<Message> linkList = new LinkedList<Message>(); 
	public DefaultTopic(String name) {
		//Queue queue=new DefaulgtQueue(name);
		//queue.setTopic(this);
		//queuesMap.put(name, queue);
		this.name=name;
	}

	public DefaultTopic(String name2, Queue queue) {
		queue.setTopic(this);
		queuesMap.put(name, queue);
		this.name=name;
	}

	@Override
	public List<Queue> getQueues() {
		List<Queue> mapValuesList = new ArrayList<Queue>(queuesMap.values());  
		return mapValuesList;
	}

	@Override
	public void attachQueue(Queue queue) {
		queuesMap.put(queue.getName(), queue);
	}

	@Override
	public void removeQueue(Queue queue) {
		queuesMap.remove(queue.getName());
	}

	@Override
	public void push(Message message) {
		linkList.push(message);
	}

	@Override
	public Message pop() {
		return linkList.pop();
	}

	public File[] getIndexFiles() {
		return indexFiles;
	}

	public void setIndexFiles(File[] indexFiles) {
		this.indexFiles = indexFiles;
	}

}

