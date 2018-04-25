package bc.blockchain.adapter.handler;

import io.netty.channel.Channel;
import bc.blockchain.adapter.handler.chain.HandlerChain;
import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;

public interface Handler {
	
	/**
	 * 处理逻辑
	 */
	public void process(Request request, Response response,HandlerChain chain);
	
	public void setCallBack(CallBack callBack);
	

}
