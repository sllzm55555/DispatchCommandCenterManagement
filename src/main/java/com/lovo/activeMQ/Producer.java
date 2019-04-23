package com.lovo.activeMQ;
import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
     * 队列的map集合，键为单位名，值为该单位的队列
     */
    private Map<String, Destination> destinationMap = new HashMap<>();
    /**
     * 将消息放入队列，传输到资源方
     * @param queueName 单位的英文名
     * @param message 消息的内容，通常是转化为json格式的对象
     * @throws InterruptedException 传输异常
     */
    public void sendMessage(String queueName, String message) throws InterruptedException {

        Destination destination = null;

        for (String key : destinationMap.keySet()) {
            if(key.equals(queueName)){
                destination = destinationMap.get(key);
                break;
            }
        }
        if(destination == null){
            destination = new ActiveMQQueue(queueName);
            destinationMap.put(queueName, destination);
        }
        jmsTemplate.convertAndSend(destination, message);
    }
}
