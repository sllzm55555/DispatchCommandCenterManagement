package com.lovo.testWebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.io.IOException;
import java.util.ArrayList;
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
            this.sessionMap.put("/ws", session);
        }else if(uri.equals("/ws02")){
            this.sessionMap.put("/ws02", session);
        }
        session.sendMessage(new TextMessage("已建立连接"));
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue001")
    public void receiveQueue(String message) throws Exception {

        WebSocketSession session = sessionMap.get("/ws");

        if(session != null && session.isOpen()){
            session.sendMessage(new TextMessage(message));
        }
    }

    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
    @JmsListener(destination="testQueue002")
    public void receiveQueue2(String message) throws Exception {

        WebSocketSession session = sessionMap.get("/ws02");
        if(session != null && session.isOpen()){
            session.sendMessage(new TextMessage(message));
        }
    }
//    事件的监听器，destination参数的值是队列的名字
    @JmsListener(destination = "eventNodealWith")
//    message就是传送过来的信息
    public  void receiveQueue4(String message) {
//        通过键在sessionMap集合里面得到WebSocketSession 对象
        WebSocketSession session = sessionMap.get("/ws");
//        如果session 对象不为空并且是开启状态
        if (session!=null&& session.isOpen()){
            try {
//                就把数据传送到页面上
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @JmsListener(destination="sb")
    public void receiveQueue3(String message) throws Exception {

        WebSocketSession session = sessionMap.get("/ws02");
        List<String> list = null;
        if(session == null){
            //如果没有session,那么只把数据存入数据库(这里模拟一个list来接收)
            list = new ArrayList<>();
            list.add(message);
            return;
        }else {
            //如果有session，那么直接推送到页面，并且保存到数据库
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

        String uri = session.getUri().toString();

        for (String key : sessionMap.keySet()) {
            WebSocketSession socketSession = sessionMap.get(key);
            if(uri.equals(key)){
                socketSession.sendMessage(new TextMessage("你说："+clientMessage+",       电脑说：滚！"));
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
