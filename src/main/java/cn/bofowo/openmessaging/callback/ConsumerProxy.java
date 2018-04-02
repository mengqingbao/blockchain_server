/**
 * Project Name:baomq
 * File Name:ProducerHandler.java
 * Package Name:cn.bofowo.openmessaging.handler
 * Date:2017年6月5日下午11:35:51
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.callback;

import io.openmessaging.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * ClassName:ProducerHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月5日 下午11:35:51 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ConsumerProxy implements Handler {
	
	private Object object;
	private String method;

	public ConsumerProxy(Object object2, String method2) {
		this.object=object2;
		this.method=method2;
	}
	
	public ConsumerProxy(){
		
	}

	@Override
	public void invoke(Message message) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = object.getClass(); 
		Method m1 = clazz.getDeclaredMethod(method,new Class[]{Message.class});
		m1.invoke(object, message);
	}
	
	public void setObject(Object object) {
		this.object = object;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}

