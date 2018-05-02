package bc.blockchain.adapter.handler.impl;

import java.util.ArrayList;
import java.util.List;

import bc.blockchain.adapter.handler.AbstractHandler;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.peer.Peer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RefreshPeerStatusHandler extends AbstractHandler {


	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.REFRESHCLIENT){
			return;
		}
		
		callBack.setResponse(response);
		callBack.setRequest(request);
		callBack.execute();
		
	}

}
