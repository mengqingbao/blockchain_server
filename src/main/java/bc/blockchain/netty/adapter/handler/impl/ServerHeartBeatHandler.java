package bc.blockchain.netty.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;
import bc.blockchain.netty.adapter.handler.AbstractHandler;

public class ServerHeartBeatHandler extends AbstractHandler {

	@Override
	public void doProcess(Request request, Response response) {
		channel.write("error code.");
		channel.close();
	}

}
