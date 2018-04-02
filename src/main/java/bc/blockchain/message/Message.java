package bc.blockchain.message;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.bofowo.openmessaging.message.MessageType;

public class Message {
	
	private final Map<String, Object> header=new HashMap();;
	
	private String userId;
	
	private String content;
	
	private Date time;
	
	private String sign;
	
	private MessageType messageType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Map<String, Object> getHeader() {
		return header;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	

}
