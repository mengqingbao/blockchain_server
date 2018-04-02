package io.openmessaging.demo;

import java.io.Serializable;

import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;

public class DefaultBytesMessage implements BytesMessage,Serializable{

    private KeyValue headers = new DefaultKeyValue();
    private KeyValue properties;
    private byte[] body;
    private String content;
    /**
     * IP地址来源
     */
    private String sourceIp;
    
    private String queue;
    
    private String topic;
    
    private String id;
    
    /**
     * 实付需要排序
     */
    private boolean isOrder;
    
    /**
     * 信息编号 升序发送
     */
    private long timestamp;

    public DefaultBytesMessage(byte[] body) {
        this.body = body;
    }
    public DefaultBytesMessage(byte[] body,String sourceIp,String queue,String topic,boolean isOrder,long timestamp) {
        this.body = body;
        this.sourceIp=sourceIp;
        this.queue=queue;
        this.topic=topic;
        this.isOrder=isOrder;
        this.timestamp=timestamp;
    }
    @Override public byte[] getBody() {
        return body;
    }

    @Override public BytesMessage setBody(byte[] body) {
        this.body = body;
        return this;
    }

    @Override public KeyValue headers() {
        return headers;
    }

    @Override public KeyValue properties() {
        return properties;
    }

    @Override public Message putHeaders(String key, int value) {
        headers.put(key, value);
        return this;
    }

    @Override public Message putHeaders(String key, long value) {
        headers.put(key, value);
        return this;
    }

    @Override public Message putHeaders(String key, double value) {
        headers.put(key, value);
        return this;
    }

    @Override public Message putHeaders(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override public Message putProperties(String key, int value) {
        if (properties == null) properties = new DefaultKeyValue();
        properties.put(key, value);
        return this;
    }

    @Override public Message putProperties(String key, long value) {
        if (properties == null) properties = new DefaultKeyValue();
        properties.put(key, value);
        return this;
    }

    @Override public Message putProperties(String key, double value) {
        if (properties == null) properties = new DefaultKeyValue();
        properties.put(key, value);
        return this;
    }

    @Override public Message putProperties(String key, String value) {
        if (properties == null) properties = new DefaultKeyValue();
        properties.put(key, value);
        return this;
    }
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public boolean isOrder() {
		return isOrder;
	}
	public void setOrder(boolean isOrder) {
		this.isOrder = isOrder;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getContent() {
		return new String(this.getBody());
	}
	@Override
	public void setContent(String content) {
		this.content=content;
	}
	@Override
	public String toString() {
		return content;
	}
	@Override
	public String getType() {
		return "COMMON";
	}
	@Override
	public String getCustomId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String respCode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setRespCode(String respCode) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
