/**
 * Project Name:baomq
 * File Name:ProduceTest.java
 * Package Name:io.openmessaging.demo
 * Date:2017年6月6日上午12:32:42
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package io.openmessaging.demo;

import io.openmessaging.Message;

/**
 * ClassName:ProduceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月6日 上午12:32:42 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ProduceTest {

	
	public void getMessage(Message message){
		System.out.println("监听结果："+message.getContent());
	}
}

