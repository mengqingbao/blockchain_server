package cn.bofowo.openmessaging.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import cn.bofowo.openmessaging.callback.Handler;
import cn.bofowo.openmessaging.context.OmContext;
import cn.bofowo.openmessaging.handler.proxy.HandlerProxy;
import cn.bofowo.openmessaging.protocol.DefaultProtocol;
import cn.bofowo.openmessaging.protocol.IProtocol;

public class DefaultServer implements IServer {
	
	
	private static final int BUFSIZE = 256;// Buffersize(bytes)
	private static final int TIMEOUT = 3000;// Waittimeout(milliseconds)
	private static final int PORT = 1693;
	private HandlerProxy handler;
	private boolean running=true;
	private OmContext context;
	//private static final String address="";

	public DefaultServer(HandlerProxy proxy) {
		
	 this.handler=handler;
		
	}

	public void bind() throws IOException {

		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		IProtocol protocol = new DefaultProtocol(BUFSIZE);
		protocol.setHandler(handler);
		while (running) {
			if (selector.select(TIMEOUT) == 0) {
				System.out.print(".");
				continue;
			}
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
				if (key.isAcceptable()) {
					protocol.processAccept(key);
				}
				if (key.isReadable()) {
					protocol.processRead(key);
				}
				if (key.isValid() && key.isWritable()) {
					protocol.processWrite(key);
				}
				keyIter.remove();
			}
		}
		
		this.close(serverSocketChannel);
	}

	@Override
	public void disConnect() {
		
	}

	@Override
	public void regist() {

	}


	@Override
	public void addListener() {

	}




	@Override
	public void connect() throws Exception {
		throw new Exception("Unsupport this method.");
	}

	@Override
	public void shutdown() {
		running=false;
	}
	
	public void close(ServerSocketChannel serverSocketChannel){
		try {
			serverSocketChannel.close();
		} catch (IOException e) {
			
		}
	}

	@Override
	public void AddContext(OmContext context) {
		this.context=context;
	}
}
