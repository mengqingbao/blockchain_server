package cn.bofowo.openmessaging.message;

import java.io.Serializable;

import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.demo.DefaultKeyValue;

public class DefaultMessage implements Message,Serializable {
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
    
    private String type;
    private String customId;
    private String respCode;
    
    /**
     * 实付需要排序
     */
    private boolean isOrder;
    
    /**
     * 信息编号 升序发送
     */
    private long timestamp;


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
	public String toString() {
		return content;
	}

	@Override
	public String getContent() {
		return null;
	}
	

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getCustomId() {
		return customId;
	}
	
	public String setCustomId(String customId) {
		return this.customId=customId;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@Override
	public String respCode() {
		return respCode;
	}
	
}
