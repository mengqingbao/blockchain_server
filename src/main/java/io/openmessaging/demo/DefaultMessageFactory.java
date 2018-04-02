package io.openmessaging.demo;

import cn.bofowo.openmessaging.ConfigConstant;
import io.openmessaging.BytesMessage;
import io.openmessaging.MessageFactory;
import io.openmessaging.MessageHeader;

public class DefaultMessageFactory implements MessageFactory {

    @Override public BytesMessage createBytesMessageToTopic(String topic, byte[] body) {
        DefaultBytesMessage defaultBytesMessage = new DefaultBytesMessage(body);
        defaultBytesMessage.putHeaders(MessageHeader.TOPIC, topic);
        long time=System.currentTimeMillis();
        defaultBytesMessage.setTopic(topic);
        defaultBytesMessage.putHeaders(ConfigConstant.MQ_ID, time);
        defaultBytesMessage.setTimestamp(time);
        return defaultBytesMessage;
    }

    @Override public BytesMessage createBytesMessageToQueue(String queue, byte[] body) {
        DefaultBytesMessage defaultBytesMessage = new DefaultBytesMessage(body);
        defaultBytesMessage.putHeaders(MessageHeader.QUEUE, queue);
        long time=System.currentTimeMillis();
        defaultBytesMessage.putHeaders(ConfigConstant.MQ_ID, time);
        defaultBytesMessage.setTimestamp(time);
        defaultBytesMessage.setQueue(queue);
        return defaultBytesMessage;
    }

}
