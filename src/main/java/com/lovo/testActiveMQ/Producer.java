package com.lovo.testActiveMQ;

import javax.jms.Destination;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王涛
 */
@Component(value = "producer")
public class Producer{
    /**
     * 注入springboot封装的工具类
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     */
    public void sendMessage() throws InterruptedException {
        Destination destination = new ActiveMQQueue("testQueue");

        String message = null;

        for (int i = 0; i < 10; i++) {
            message = "第"+i+"条消息";
            //将消息放入队列
            System.out.println("客户端主动发送消息");
            jmsTemplate.convertAndSend(destination, message);
            Thread.sleep(1000);
        }
    }
 


}
