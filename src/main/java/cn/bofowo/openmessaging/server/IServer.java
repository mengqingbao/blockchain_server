package cn.bofowo.openmessaging.server;

import java.io.IOException;

import cn.bofowo.openmessaging.context.OmContext;

public interface IServer {
	
	public void regist();
	
	public void connect() throws Exception;
	
	public void disConnect();
	
	public void addListener();
	
	void bind() throws IOException;
	
	void shutdown();

	public void AddContext(OmContext context);
	
}
