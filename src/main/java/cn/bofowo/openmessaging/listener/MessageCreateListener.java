/**
 * Project Name:baomq
 * File Name:MessageCreateListener.java
 * Package Name:cn.bofowo.openmessaging.listener
 * Date:2017年5月12日上午10:16:29
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.listener;

import io.openmessaging.Message;
import io.openmessaging.demo.DefaultBytesMessage;

/**
 * ClassName:MessageCreateListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月12日 上午10:16:29 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MessageCreateListener implements OmListener {

	private Message message;
	@Override
	public void excute() {
		
	}

	@Override
	public void setObject(Object obj) {
		if(obj instanceof DefaultBytesMessage){
			this.message=(DefaultBytesMessage) obj;
		}
	}

}

