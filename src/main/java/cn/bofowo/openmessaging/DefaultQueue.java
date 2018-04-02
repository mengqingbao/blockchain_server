/**
 * Project Name:baomq
 * File Name:DefaultQueue.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月5日下午4:47:49
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import io.openmessaging.Message;

import java.io.File;
import java.util.LinkedList;
import java.util.Stack;

/**
 * ClassName:DefaultQueue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月5日 下午4:47:49 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DefaultQueue implements Queue{

	private String name;
	private Topic topic;
	private  LinkedList<Message> linkList = new LinkedList<Message>(); 
	private File[] indexFiles;
	public DefaultQueue(String name){
		this.name=name;
	}
	public DefaultQueue(String name,Topic topic){
		this.name=name;
		this.topic=topic;
	}
	@Override
	public void setTopic(Topic topic) {
		this.topic=topic;
	}

	@Override
	public Topic getTopic() {
		return topic;
	}

	@Override
	public void push(Message message) {
		linkList.push(message);
	}

	@Override
	public Message pop() {
		return linkList.pop();
	}

	public String getName() {
		return name;
	}
	public File[] getIndexFiles() {
		return indexFiles;
	}
	public void setIndexFiles(File[] indexFiles) {
		this.indexFiles = indexFiles;
	}
	
	
}

