/**
 * Project Name:baomq
 * File Name:BootStrap.java
 * Package Name:cn.bofowo.openmessaging
 * Date:2017年5月9日下午2:22:17
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import java.io.IOException;

import cn.bofowo.openmessaging.server.DefaultServer;
import cn.bofowo.openmessaging.server.IServer;
import io.openmessaging.InvokeContext;
import io.openmessaging.KeyValue;
import io.openmessaging.ServiceEndPoint;
import io.openmessaging.ServiceLifecycle;
import io.openmessaging.ServiceLoadBalance;
import io.openmessaging.observer.Observer;

/**
 * ClassName:BootStrap <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月9日 下午2:22:17 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class BootStrap implements ServiceLifecycle, ServiceEndPoint  {

	
	public BootStrap(){
		this.init();
	}
	
	public void init(){
		IServer server=new DefaultServer(null);
		try {
			server.bind();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	
	@Override
	public void publish(Object service) {
		
	}

	@Override
	public void publish(Object service, KeyValue properties) {
		
	}

	@Override
	public <T> T bind(Class<T> type) {
		return null;
	}

	@Override
	public <T> T bind(Class<T> type, KeyValue properties) {
		return null;
	}

	@Override
	public <T> T bind(Class<T> type, KeyValue properties, ServiceLoadBalance serviceLoadBalance) {
		return null;
	}

	@Override
	public void addObserver(Observer observer) {
		
	}

	@Override
	public void deleteObserver(Observer observer) {
		
	}

	@Override
	public InvokeContext invokeContext() {
		return null;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void shutdown() {
		
	}


	
}

