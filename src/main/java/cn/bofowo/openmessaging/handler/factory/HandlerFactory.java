package cn.bofowo.openmessaging.handler.factory;

import java.util.ArrayList;
import java.util.List;

import cn.bofowo.openmessaging.handler.Handler;
import cn.bofowo.openmessaging.handler.impl.DefaultMessageHandler;
import cn.bofowo.openmessaging.handler.proxy.HandlerProxy;


public class HandlerFactory {

	
	public static HandlerFactory getInstance(){
		return new HandlerFactory();
	}
	

	private List<Handler> handlers = new ArrayList<Handler>();

	
	public HandlerProxy createProducerHandler(){
		
		HandlerProxy proxy = new HandlerProxy(null);
		return proxy;
	}

	public HandlerProxy createRebateHandler() throws Exception {
		throw new Exception("It's not support this structure method current,Maybe in future.");
	}

	public HandlerProxy createRebateHandler(long caseId) {
	
		HandlerProxy proxy = new HandlerProxy(new DefaultMessageHandler());
		return proxy;
	}

}
