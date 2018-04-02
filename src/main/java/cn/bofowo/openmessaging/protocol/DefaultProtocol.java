package cn.bofowo.openmessaging.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import cn.bofowo.openmessaging.BaomqProducer;
import cn.bofowo.openmessaging.handler.proxy.HandlerProxy;
import io.openmessaging.BytesMessage;
import io.openmessaging.KeyValue;
import io.openmessaging.Producer;
import io.openmessaging.demo.DefaultBytesMessage;
import io.openmessaging.demo.DefaultKeyValue;

public class DefaultProtocol implements IProtocol{
	private int bufSize;
	private HandlerProxy handler;

	public DefaultProtocol(int bufSize) {
		this.bufSize = bufSize;
	}

	public void processAccept(SelectionKey key) throws IOException {
		//System.out.println("client is connected success");
		ServerSocketChannel serverSocketChannel =  (ServerSocketChannel) key.channel();
		SocketChannel clientChannel = serverSocketChannel.accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
		//handler.execute(serverSocketChannel);
	}

	public void processRead(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		//ByteBuffer buf = ByteBuffer.allocate(1024);
		StringBuffer content=new StringBuffer();
        long bytesRead = socketChannel.read(buf);
        while(bytesRead>0){
        buf.flip();
        while(buf.hasRemaining()){
        	content.append((char)buf.get());
        }
         System.out.println();
       buf.clear();
       bytesRead = socketChannel.read(buf);
       }
       if(bytesRead == -1){
    	   socketChannel.close();
       }
       System.out.println(content.toString());
       KeyValue properties = new DefaultKeyValue();
       properties.put("STORE_PATH", "/home/hanahyu/mengqingbao/alicode/message");
       //Producer producer = new DefaultProducer(properties);
       ///DefaultQueueFactory messageFactory=new DefaultQueueFactory();
      // DefaultMessageFactory messageFactory=new DefaultMessageFactory();
       BytesMessage message=new DefaultBytesMessage(null);
       message.setContent(content.toString());
     // System.out.println(message.getContent());
//       try {
//		handler.invoke(message);
//	} catch (NoSuchMethodException e) {
//		e.printStackTrace();
//		
//	} catch (SecurityException e) {
//		e.printStackTrace();
//	} catch (IllegalAccessException e) {
//		e.printStackTrace();
//	} catch (IllegalArgumentException e) {
//		e.printStackTrace();
//	} catch (InvocationTargetException e) {
//		e.printStackTrace();
//	}
	}

	public void processWrite(SelectionKey key) throws IOException {
		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();
		SocketChannel clientChannel = (SocketChannel) key.channel();
		clientChannel.write(buf);
		if (!buf.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		}
		buf.compact();
		//ServerSocketChannel serverSocketChannel =  (ServerSocketChannel) key.channel();
		//handler.execute(serverSocketChannel);
	}

	@Override
	public void setHandler(HandlerProxy handler) {
		this.handler = handler;
	}
}
