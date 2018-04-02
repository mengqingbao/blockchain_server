package cn.bofowo.openmessaging.netty;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cn.bofowo.openmessaging.listener.OmListener;

public class NettyClientContextSupport {
	
	private Map<String,Vector<OmListener>> listeners = new HashMap<String,Vector<OmListener>>();
	
	private NettyClientContext context;
	public NettyClientContextSupport(NettyClientContext context){
		this.context=context;
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
