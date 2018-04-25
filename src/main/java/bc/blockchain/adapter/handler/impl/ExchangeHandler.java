package bc.blockchain.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.adapter.handler.AbstractHandler;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.server.BlockChainContext;

public class ExchangeHandler extends AbstractHandler {


	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.EXCHANAGE){
			return;
		}
		callBack.setRequest(request);
		callBack.execute();
		
		System.out.println("数据交换到对方服务端");
	}

}
