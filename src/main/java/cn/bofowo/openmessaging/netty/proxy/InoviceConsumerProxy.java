package cn.bofowo.openmessaging.netty.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.openmessaging.Message;

public class InoviceConsumerProxy {
	
	private Object obj;
	private String method;
	private Message message;
	public InoviceConsumerProxy(Object ref, String method) {
		this.obj=ref;
		this.method=method;
		
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public void execute(){
		 	Class clazz = obj.getClass(); 
	        Method m1;
			try {
				m1 = clazz.getMethod(method, Message.class);
		        m1.invoke(obj,message); 
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}  
		
		}
	@Override
	public boolean equals(Object obj) {
		InoviceConsumerProxy proxy=(InoviceConsumerProxy)obj;
		if(this.obj.getClass().getName().equals(obj.getClass().getName())&&this.getMethod().equals(proxy.getMethod())){
			return true;
		}
		return false;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	}