package com.lovo.testActiveMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author sllzm
 */
@Component(value = "consumer")
public class Consumer {
    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue")
    public void receiveQueue(String message) {
        System.out.println("成功接受:" + message);
    }


}
