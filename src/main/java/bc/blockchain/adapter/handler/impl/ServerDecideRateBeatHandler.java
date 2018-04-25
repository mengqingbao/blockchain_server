package bc.blockchain.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.adapter.handler.AbstractHandler;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;

public class ServerDecideRateBeatHandler extends AbstractHandler {

	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.COMMON){
			return;
		}
	}

}
