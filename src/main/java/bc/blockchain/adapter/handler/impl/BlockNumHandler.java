package bc.blockchain.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.adapter.handler.AbstractHandler;
import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;

public class BlockNumHandler extends AbstractHandler {

	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.CHECK){
			return;
		}
	}

}
