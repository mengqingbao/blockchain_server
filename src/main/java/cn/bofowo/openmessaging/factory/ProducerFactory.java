package cn.bofowo.openmessaging.factory;

import cn.bofowo.openmessaging.BaomqProducer;
import cn.bofowo.openmessaging.context.OmApplicationContext;
import cn.bofowo.openmessaging.context.OmContext;
import cn.bofowo.openmessaging.netty.NettyServerContext;
import cn.bofowo.openmessaging.netty.proxy.ServerProxy;
import io.openmessaging.Producer;

public class ProducerFactory {
	private static Producer producer;
	private OmContext context;

	public ProducerFactory() {
		if (producer == null) {
			producer = new BaomqProducer();
			if(context==null){
				context = OmApplicationContext.getInstance();
			}
		}
	}
	public Producer createProducer() {
		return producer;
	}

}
