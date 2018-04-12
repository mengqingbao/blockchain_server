package bc.blockchain.netty.adapter.handler.impl;

import java.util.Date;

import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.netty.adapter.handler.AbstractHandler;
import bc.blockchain.peer.Peer;

import com.alibaba.fastjson.JSONObject;

public class RefreshPeerStatusHandler extends AbstractHandler {


	@Override
	public void doProcess(Request request, Response response) {
		if(request.getrequestType()!=RequestType.REFRESHCLIENT){
			return;
		}
		JSONObject obj=JSONObject.parseObject(request.getContent());
		Peer peer=new Peer(obj.getString("ip"),obj.getInteger("port"),new Date());
		callBack.setPeer(peer);
		callBack.execute();
		response.setCode("200");
		response.setContent("{\"status\"|:\"success\"}");
		
	}

}
