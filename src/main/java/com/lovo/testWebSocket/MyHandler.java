package com.lovo.testWebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 建立连接的类
 * @author 王涛
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class MyHandler implements WebSocketHandler {


    private WebSocketSession socketSession;

    public WebSocketSession getSocketSession() {
        return socketSession;
    }

    /**
     * 连接建立之后干啥
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("连接建立成功");
        socketSession = session;
        //服务器向客户端发送消息
        for (int i = 0; i < 10; i++) {
            session.sendMessage(new TextMessage("已经与客户端建立连接" + i));
            Thread.sleep(100);
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
        System.out.println(clientMessage);

        if (null!=this.socketSession  && this.socketSession.isOpen()) {
            //回复客户端消息
            this.socketSession.sendMessage(new TextMessage("{'name:'serverMessage','message':'我是服务器，我已经接收到了你的消息"+clientMessage+"'}"));
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
