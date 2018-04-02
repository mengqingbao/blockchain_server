/**
 * Project Name:baomq
 * File Name:OmApplicationContextSupport.java
 * Package Name:cn.bofowo.openmessaging.context
 * Date:2017年5月12日上午10:19:03
 * Copyright (c) 2017, BOFOWO Technology Co., Ltd. All Rights Reserved.
 *
*/

package cn.bofowo.openmessaging.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cn.bofowo.openmessaging.ConfigConstant;
import cn.bofowo.openmessaging.listener.OmListener;
import cn.bofowo.openmessaging.listener.QueueCreateListener;
import cn.bofowo.openmessaging.listener.QueuePopMessageListener;
import cn.bofowo.openmessaging.listener.QueueSendListener;
import cn.bofowo.openmessaging.listener.TopicCreateListener;
import cn.bofowo.openmessaging.listener.TopicSendListener;

/**
 * ClassName:OmApplicationContextSupport <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月12日 上午10:19:03 <br/>
 * @author   mqb
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class OmApplicationContextSupport {
	//监听程序
	private Map<String,Vector<OmListener>> listeners = new HashMap<String,Vector<OmListener>>();
	private OmContext context;
	public OmApplicationContextSupport(OmApplicationContext context){
		this.context=context;
		
		QueuePopMessageListener qpml=new QueuePopMessageListener();
		this.addListener(ConfigConstant.MQ_MESSAGE_POP_EVENT, qpml);
		QueueSendListener qsl=new QueueSendListener(context.getServer());
		this.addListener(ConfigConstant.SEND_QUEUE_EVENT, qsl);
		TopicCreateListener tcl=new TopicCreateListener();
		this.addListener(ConfigConstant.CREATE_MESSAGE_TO_TOPIC_EVENT, tcl);
		QueueCreateListener qcl=new QueueCreateListener();
		this.addListener(ConfigConstant.CREATE_MESSAGE_TO_QUEUE_EVENT, qcl);
		
		TopicSendListener tsl=new TopicSendListener();
		this.addListener(ConfigConstant.SEND_TOPIC_EVENT, tsl);
		
	}
	public void addListener(String event,OmListener listener) {
		
		Vector<OmListener> lis=listeners.get(event);
		if(lis==null||lis.isEmpty()){
			lis=new Vector<OmListener>();
		}
		lis.add(listener);
		listeners.put(event,lis);
	}

	public void fireEvent(String event,Object obj) {
		Vector<OmListener> ts=listeners.get(event);
		for (OmListener t :ts ) {
			t.setObject(obj);
			t.excute();
		}
	}

}

