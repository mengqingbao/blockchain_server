package bc.blockchain.netty.adapter.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import cn.bofowo.openmessaging.netty.NettyServerContext;
import bc.blockchain.message.Message;
import bc.blockchain.server.BlockChainContext;

public abstract class AbstractHandler implements Handler {

	private Handler handler;
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	protected BlockChainContext blockChainContext;
	protected Channel channel;
	protected Message message;

	@Override
	public abstract void process(Channel channel, Message messsageInfo);

	@Override
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	@Override
	public Handler nextHandler() {
		return handler;
	}

	public Object getObject(String key) {
		if (dataMap != null) {
			return dataMap.get(key);
		}
		return null;
	}

	public void addObject(String key, Object obj) {
		dataMap.put(key, obj);
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public void setBlockChainContext(BlockChainContext context) {
		this.blockChainContext = context;

	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
