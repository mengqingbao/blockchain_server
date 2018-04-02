/**
 * Project Name:baomq
 * File Name:DefaultConsumer.java
 * Package Name:cn.bofowo.openmessaging.consumer
 * Date:2017年6月5日下午11:26:41
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging;

import java.util.HashMap;
import java.util.Map;


import bc.blockchain.netty.adapter.NettyClientHandler;
import cn.bofowo.openmessaging.netty.ServerChannelFactory;
import io.openmessaging.Message;
import io.openmessaging.demo.DefaultBytesMessage;

/**
 * ClassName:DefaultConsumer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月5日 下午11:26:41 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public abstract class BaomqConsumer implements IConsumer{

	
	@Override
	public String getChannel() {
		return null;
	}

	@Override
	public void execute(Message message) {
		this.internalExecute(message);
	}

	public abstract void internalExecute(Message message);
}

