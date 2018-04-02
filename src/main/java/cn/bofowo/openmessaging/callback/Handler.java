/**
 * Project Name:baomq
 * File Name:Handler.java
 * Package Name:cn.bofowo.openmessaging.callback
 * Date:2017年6月5日下午11:53:44
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.callback;

import java.lang.reflect.InvocationTargetException;

import io.openmessaging.Message;

/**
 * ClassName:Handler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月5日 下午11:53:44 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface Handler {
	public void invoke(Message message) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}

