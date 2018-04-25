package bc.blockchain.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.adapter.handler.AbstractHandler;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;

public class ServerHeartBeatHandler extends AbstractHandler {

	@Override
	public void doProcess(Request request, Response response) {
		channel.write("error code.");
		channel.close();
	}

}
