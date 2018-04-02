package cn.bofowo.openmessaging.netty;

import java.util.Date;

import cn.bofowo.openmessaging.netty.uri.URI;
import io.netty.channel.Channel;

public class ChannelWrapper {
	private Channel channel;
	private String Id;
	private URI uri;
	private String queueName;
	private String topic;
	private boolean isLive;
	private Date lastCheckDate;
	
	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public URI getUri() {
		return uri;
	}


	public void setUri(URI uri) {
		this.uri = uri;
	}


	public String getQueueName() {
		return queueName;
	}


	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public boolean isLive() {
		return isLive;
	}


	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}


	public Date getLastCheckDate() {
		return lastCheckDate;
	}


	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}


	public void reConnect(){
		while(true){
		//channel;
			//Thread.currentThread().sleep(5000l);
		}
	}

}
