package cn.bofowo.openmessaging.handler;

import java.util.Map;

public interface Handler {
	
	/**
	 * 处理逻辑
	 */
	public void process(Map<String,Object> datas);
	
	/**
	 * 构架handler链表
	 * @param handler
	 */
	public void setHandler(Handler handler);
	
	
	public Handler nextHandler();

}
