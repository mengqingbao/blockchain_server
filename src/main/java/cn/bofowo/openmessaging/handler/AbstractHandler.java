package cn.bofowo.openmessaging.handler;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHandler implements Handler {

	private Handler handler;
	
	private Map<String,Object> dataMap=new HashMap<String,Object>();
	
	@Override
	public abstract void process(Map<String,Object> datas);

	@Override
	public void setHandler(Handler handler) {
		this.handler=handler;
	}

	@Override
	public Handler nextHandler() {
		return handler;
	}

	public Object getObject(String key){
		if(dataMap!=null){
			return dataMap.get(key);
		}
		return null;
	}
	
	public void addObject(String key,Object obj){
		dataMap.put(key, obj);
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	
}
