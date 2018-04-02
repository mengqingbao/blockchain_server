package cn.bofowo.openmessaging.filter;

import java.util.LinkedList;

public class MessageFilterChain {
	public LinkedList<MessageFilter> rabateLink=new LinkedList<MessageFilter>();
	private int position=0;
	public MessageFilterChain addFilter(MessageFilter filter){
		rabateLink.add(filter);
		return this;
		
	}
	public MessageFilterCondition doFilter(MessageFilterCondition datas){
		MessageFilter f=rabateLink.get(position);
		if(position>rabateLink.size()){
			f.doFilter(datas, this);
		}
		return datas;
	}
}
