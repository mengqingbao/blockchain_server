package bc.blockchain.callback.impl;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.constant.BcConstant;
import bc.blockchain.peer.Peer;
import bc.blockchain.server.BlockChainContext;

public class SimpleCallBack implements CallBack {

	private BlockChainContext context;
	private RequestType command;
	private Peer peer;
	private Request request;
	private Response response;

	public SimpleCallBack(BlockChainContext context, RequestType command) {
		this.context = context;
		this.command = command;
	}

	@Override
	public Response execute() {

		switch (command) {
		case REG:
			peer.setClientId(request.getClientId());
			context.regClient(peer);
			break;
		case REFRESHCLIENT:
			context.freshClient(request.getClientId());
			break;

		case COMMON:

			break;

		case HEARTBEAT:

			break;
		case EXPORT:
			List<String> ids=context.export();
			response.setContent(JSONArray.toJSONString(ids));
			response.setCode(BcConstant.SUCCESS);
			break;
		case EXCHANAGE:
			context.exchange(request);
			break;

		default:
			break;
		}
		
		return response;
	}

	public void setPeer(Peer peer2) {
		this.peer = peer2;
	}

	@Override
	public Peer getPeer() {
		return peer;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	
}
