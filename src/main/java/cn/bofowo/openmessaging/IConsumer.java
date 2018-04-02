package cn.bofowo.openmessaging;

import io.openmessaging.Message;

public interface IConsumer {
	public String getChannel();
	void execute(Message message);
}
