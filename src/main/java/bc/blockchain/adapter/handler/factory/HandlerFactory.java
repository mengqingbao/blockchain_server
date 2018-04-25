package bc.blockchain.adapter.handler.factory;

import bc.blockchain.adapter.handler.Handler;
import bc.blockchain.adapter.handler.chain.HandlerChain;
import bc.blockchain.adapter.handler.proxy.HandlerProxy;
import bc.blockchain.callback.CallBack;
import bc.blockchain.constant.BcConstant;
import bc.blockchain.server.BlockChainContext;
import bc.blockchain.util.PropertiesUtil;

public class HandlerFactory {
	
	private static HandlerFactory factory;
	public HandlerFactory() {
	}
	public static HandlerFactory getInstance(){
		if(factory==null){
			factory=new HandlerFactory();
		}
		return factory;
	}
	public HandlerProxy createDefaultProxy(CallBack callBack) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PropertiesUtil prop=new PropertiesUtil(BcConstant.SERVER_CONFIG);
		String handlerStr=prop.getProperty(BcConstant.SERVER_HANDLER);
		String[] handlerStrs=handlerStr.split(";");
		HandlerChain handlerChain = new HandlerChain();
		for(String hder:handlerStrs){
			Class clzz=Class.forName(hder);
			Handler handler=(Handler) clzz.newInstance();
			handler.setCallBack(callBack);
			handlerChain.addHandler(handler);
		}
		HandlerProxy proxy = new HandlerProxy(handlerChain);
		return proxy;
	}


	public HandlerChain createChain(CallBack callBack) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PropertiesUtil prop=new PropertiesUtil(BcConstant.SERVER_CONFIG);
		String handlerStr=prop.getProperty(BcConstant.SERVER_HANDLER);
		String[] handlerStrs=handlerStr.split(";");
		HandlerChain handlerChain = new HandlerChain();
		for(String hder:handlerStrs){
			Class clzz=this.getClass().getClassLoader().loadClass(hder);
			Handler handler=(Handler) clzz.newInstance();
			handler.setCallBack(callBack);
			handlerChain.addHandler(handler);
		}
		return handlerChain;
	}

}
