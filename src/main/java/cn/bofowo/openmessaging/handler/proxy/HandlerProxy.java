package cn.bofowo.openmessaging.handler.proxy;

import java.util.Map;

import cn.bofowo.openmessaging.handler.Handler;

public class HandlerProxy {
	private Handler handler;
	public HandlerProxy(Handler handler){
		this.handler=handler;
	}
	
	public void doHandle(Map<String,Object>  datas){
		while(handler!=null){
			handler.process(datas);
			this.handler=handler.nextHandler();
		}
	}
	

}
