package bc.blockchain.callback;

import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;
import bc.blockchain.peer.Peer;

public interface CallBack {
	public Response execute();
	public void setPeer(Peer peer2);
	public Peer getPeer();
	public void setRequest(Request request);
	public void setResponse(Response response);
}
