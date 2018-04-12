package bc.blockchain.callback.impl;

import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.peer.Peer;
import bc.blockchain.server.BlockChainContext;

public class SimpleCallBack implements CallBack {

	private BlockChainContext context;
	private RequestType command;
	private Peer peer;

	public SimpleCallBack(BlockChainContext context, RequestType command) {
		this.context = context;
		this.command = command;
	}

	@Override
	public void execute() {

		switch (command) {
		case REG:
			context.regClient(peer);
			break;
		case REFRESHCLIENT:
			context.freshClient(peer);
			break;

		case COMMON:

			break;

		case HEARTBEAT:

			break;

		case EXCEPTION:

			break;

		default:
			break;
		}
	}

	public void setPeer(Peer peer2) {
		this.peer = peer2;
	}

	@Override
	public Peer getPeer() {
		return peer;
	}

}
