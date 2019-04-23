package com.lovo.testWebSocket;

import com.lovo.testActiveMQ.Producer;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建立连接的类
 * @author 王涛
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
@Component
public class MyHandler implements WebSocketHandler {

    @Resource(name = "producer")
    Producer producer;

    private Map<String,WebSocketSession> sessionMap = new HashMap<>();

    /**
     * 连接建立之后干啥
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String uri = session.getUri().toString();

        if(uri.equals("/ws")){
            this.sessionMap.put("ws", session);
        }else if(uri.equals("/ws02")){
            this.sessionMap.put("ws02", session);
        }
        session.sendMessage(new TextMessage("已经与客户端建立连接"));

        //把数据放入队列 testQueue 和 testQueue2
//        producer.sendMessage();
//        producer.sendMessage2();
        //服务器向客户端发送消息
        /*for (int i = 0; i < 10; i++) {
            session.sendMessage(new TextMessage("已经与客户端建立连接" + i));
            Thread.sleep(1000);
        }*/
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue001")
    public void receiveQueue(String message) throws Exception {

        WebSocketSession session = sessionMap.get("ws");
        session.sendMessage(new TextMessage(message));
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue002")
    public void receiveQueue2(String message) throws Exception {

        WebSocketSession session = sessionMap.get("ws02");
        session.sendMessage(new TextMessage(message));
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

        for (String key : sessionMap.keySet()) {
            WebSocketSession socketSession = sessionMap.get(key);
            String uri = session.getUri().toString();
            if(uri.equals(key)){
                socketSession.sendMessage(new TextMessage("服务器收到信息"+clientMessage+",服务器返回信息：OK"));
            }
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
