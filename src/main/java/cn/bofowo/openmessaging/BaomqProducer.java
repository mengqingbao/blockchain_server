/**
 * Project Name:baomq
 * File Name:DefaultProducer.java
 * Package Name:cn.bofowo.openmessaging.producer
 * Date:2017年6月5日下午11:26:20
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import io.openmessaging.BatchToPartition;
import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.Producer;
import io.openmessaging.Promise;

import java.io.IOException;

import cn.bofowo.openmessaging.context.OmApplicationContext;
import cn.bofowo.openmessaging.context.OmContext;
import cn.bofowo.openmessaging.handler.factory.HandlerFactory;
import cn.bofowo.openmessaging.handler.proxy.HandlerProxy;
import cn.bofowo.openmessaging.netty.proxy.ServerProxy;
import cn.bofowo.openmessaging.server.DefaultServer;
import cn.bofowo.openmessaging.server.IServer;

/**
 * ClassName:DefaultProducer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月5日 下午11:26:20 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class BaomqProducer implements Producer {
	public BaomqProducer(){
	}
	@Override
	public void send(Message message) {
		ServerProxy.instance().send(message);
	}


}

