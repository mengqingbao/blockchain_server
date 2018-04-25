package bc.blockchain.adapter.handler.proxy;

import io.netty.channel.Channel;
import bc.blockchain.adapter.handler.chain.HandlerChain;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;

public class HandlerProxy {
	private HandlerChain handlerChain;
	public HandlerProxy(HandlerChain handler){
		this.handlerChain=handler;
	}
	
	public Response doHandle(Request request){
			Response response=new Response();
			handlerChain.doHandle(request, response,handlerChain);
			return response;
	}
	

}
