package bc.blockchain.netty.adapter.handler.impl;

import io.netty.channel.Channel;

import java.util.Map;

import bc.blockchain.message.Message;
import bc.blockchain.netty.adapter.handler.AbstractHandler;

public class SynicDataHeartBeatHandler extends AbstractHandler {

	@Override
	public void process(Channel channel, Message messsageInfo) {
		channel.write("error code.");
		channel.close();
	}

}
