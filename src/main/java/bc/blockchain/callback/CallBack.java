package bc.blockchain.callback;

import bc.blockchain.common.request.RequestType;
import bc.blockchain.peer.Peer;

public interface CallBack {
	public void execute();
	public void setPeer(Peer peer2);
	public Peer getPeer();
}
