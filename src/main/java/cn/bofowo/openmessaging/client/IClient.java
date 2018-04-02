package cn.bofowo.openmessaging.client;

import java.io.IOException;

public interface IClient {

	void send(String message) throws IOException;

}
