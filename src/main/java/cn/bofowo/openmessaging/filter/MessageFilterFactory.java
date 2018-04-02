package cn.bofowo.openmessaging.filter;

public class MessageFilterFactory {
	
	public static MessageFilterFactory getInstance(){
		return new MessageFilterFactory();
	}
	
	public MessageFilterFactory(){
	}
	
	public MessageFilterChain initConditionChain(){
		MessageFilterChain rfc=new MessageFilterChain();
		return rfc;
	}

}
