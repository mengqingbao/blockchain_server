package cn.bofowo.cn.test;

import java.io.IOException;

import cn.bofowo.openmessaging.server.DefaultServer;
import cn.bofowo.openmessaging.server.IServer;

public class ServerMain {

	public static void main(String[] args) {
		IServer server=new DefaultServer(null);
		try {
			server.bind();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
