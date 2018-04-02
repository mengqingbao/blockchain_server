/**
 * Project Name:baomq
 * File Name:Handler.java
 * Package Name:cn.bofowo.openmessaging.channel.handler
 * Date:2017年6月3日下午6:59:21
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.channel.handler;

import java.nio.channels.SocketChannel;

/**
 * ClassName:Handler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月3日 下午6:59:21 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface Handler {
	public void execute(SocketChannel socketChannel);
}

