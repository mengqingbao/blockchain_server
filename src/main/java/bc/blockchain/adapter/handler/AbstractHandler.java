package bc.blockchain.adapter.handler;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

import bc.blockchain.adapter.handler.chain.HandlerChain;
import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;

public abstract class AbstractHandler implements Handler {

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	protected CallBack callBack;
	protected Channel channel;

	@Override
	public void process(Request request, Response response,HandlerChain chain){
		doProcess(request,response);
		chain.doHandle(request, response, chain);
	};

	public abstract void doProcess(Request request, Response response);

	public Object getObject(String key) {
		if (dataMap != null) {
			return dataMap.get(key);
		}
		return null;
	}

	public void addObject(String key, Object obj) {
		dataMap.put(key, obj);
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void setCallBack(CallBack callBack) {
		this.callBack=callBack;
	}


}
