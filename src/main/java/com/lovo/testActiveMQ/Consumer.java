package com.lovo.testActiveMQ;

import com.lovo.testWebSocket.MyHandler;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.jms.Destination;
import java.io.IOException;

/**
 * @author 王涛
 */
@Component(value = "consumer")
public class Consumer {
//
//    @Autowired
//    private MyHandler myHandler;

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue")
    public void receiveQueue(String message) throws IOException {
        System.out.println("成功接受:" + message);

//        WebSocketSession session = myHandler.getSocketSession();
//
//        if (null!=session  && session.isOpen()) {
//            //回复客户端消息
//            session.sendMessage(new TextMessage("{'name:'serverMessage','message':'"+message+"'}"));
//        }

    }


}
