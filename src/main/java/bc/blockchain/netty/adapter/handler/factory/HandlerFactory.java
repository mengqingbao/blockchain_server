package bc.blockchain.netty.adapter.handler.factory;

import bc.blockchain.constant.BcConstant;
import bc.blockchain.netty.adapter.handler.Handler;
import bc.blockchain.netty.adapter.handler.chain.HandlerChain;
import bc.blockchain.netty.adapter.handler.proxy.HandlerProxy;
import bc.blockchain.server.BlockChainContext;
import bc.blockchain.util.PropertiesUtil;

public class HandlerFactory {
	
	private static HandlerFactory factory;
	private BlockChainContext blockChainContext;;
	
	public HandlerFactory(BlockChainContext blockChainContext) {
		this.blockChainContext=blockChainContext;
	}


	public static HandlerFactory getInstance(BlockChainContext blockChainContext){
		if(factory==null){
			factory=new HandlerFactory(blockChainContext);
		}
		return factory;
	}
	public HandlerProxy createDefaultProxy() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PropertiesUtil prop=new PropertiesUtil(BcConstant.SERVER_CONFIG);
		String handlerStr=prop.getProperty(BcConstant.SERVER_HANDLER);
		String[] handlerStrs=handlerStr.split(";");
		HandlerChain handlerChain = new HandlerChain();
		for(String hder:handlerStrs){
			Class clzz=Class.forName(hder);
			Handler handler=(Handler) clzz.newInstance();
			handlerChain.addHandler(handler);
		}
		HandlerProxy proxy = new HandlerProxy(handlerChain);
		return proxy;
	}
	
}
