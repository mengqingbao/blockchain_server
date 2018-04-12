package bc.blockchain.netty.adapter.handler.impl;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.netty.adapter.handler.AbstractHandler;
import bc.blockchain.server.BlockChainContext;

public class RegistHandler extends AbstractHandler {
	

	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.REG){
			return;
		}
		response.setCode("200");
		response.setContent(callBack.getPeer().toString());
		response.putHeader(request.getrequestType());
		callBack.execute();
	}

}
