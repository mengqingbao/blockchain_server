package cn.bofowo.openmessaging.protocol;

import java.io.IOException;
import java.nio.channels.SelectionKey;

import cn.bofowo.openmessaging.handler.proxy.HandlerProxy;

public interface IProtocol {
	void processAccept(SelectionKey key) throws IOException;

	void processRead(SelectionKey key) throws IOException;

	void processWrite(SelectionKey key) throws IOException;
	public void setHandler(HandlerProxy handler);
}
