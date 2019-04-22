package com.lovo.testWebSocket;

import com.alibaba.fastjson.JSONObject;
import com.lovo.testActiveMQ.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.io.IOException;
import java.util.List;

/**
 * 建立连接的类
 * @author 王涛
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
@Component
public class MyHandler implements WebSocketHandler {

    private WebSocketSession session;

    @Autowired
    private Producer producer;
    /**
     * 连接建立之后干啥
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.session = session;
        session.sendMessage(new TextMessage("已经与客户端建立连接"));
        producer.sendMessage();
        //服务器向客户端发送消息
        /*for (int i = 0; i < 10; i++) {
            session.sendMessage(new TextMessage("已经与客户端建立连接" + i));
            Thread.sleep(1000);
        }*/
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue")
    public void receiveQueue(String message) throws Exception {
        if(session != null){
            session.sendMessage(new TextMessage(message));
        }
    }

    /**
     * 处理要发送的信息，在客户端通过websocket发送的消息都会被拦截，用户根据自己的需要来处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        //打印从客户端发送的消息
        String clientMessage = message.getPayload().toString();

        if (null!=this.session  && this.session.isOpen()) {
            //回复客户端消息
            this.session.sendMessage(new TextMessage("我是服务器，我已经接收到了你的消息"+ clientMessage));
        }
    }

    /**
     * 如果消息传输错误做什么
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("消息传输错误!");
    }

    /**
     * 连接关闭时做什么
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("连接关闭!");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
