package bc.blockchain.netty.adapter.handler.proxy;

import io.netty.channel.Channel;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;
import bc.blockchain.netty.adapter.handler.chain.HandlerChain;

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
