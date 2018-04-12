package bc.blockchain.util;

import bc.blockchain.callback.CallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.response.Response;
import bc.blockchain.dispatcher.Dispatcher;

public class DispatherUtil {

	private Dispatcher dispatcher;
	private DispatherUtil util;
	private CallBack callBack;
	
	public DispatherUtil(CallBack callBack){
		this.callBack=callBack;
		dispatcher=new Dispatcher(callBack);
	}
	public Response doService(Request req) {
		Response response =new Response();
		try {
			dispatcher.doService(req,response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return response;
	}

}
