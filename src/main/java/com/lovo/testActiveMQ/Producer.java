package com.lovo.testActiveMQ;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
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

        Destination destination = new ActiveMQQueue("testQueue001");

        String message = null;

        for (int i = 0; i < 10; i++) {
            message = "第"+(i+1)+"条消息";
            //将消息放入队列
            jmsTemplate.convertAndSend(destination, message+"===队列1===传输");
            Thread.sleep(1000);
        }
    }

    public void sendMessage2() throws InterruptedException {
        Destination destination = new ActiveMQQueue("testQueue002");
        String message = null;
        for (int i = 0; i < 10; i++) {
            message = "第"+(i+1)+"条消息";
            //将消息放入队列
            jmsTemplate.convertAndSend(destination, message+"---队列2---传输");
            Thread.sleep(1000);
        }
    }






}
