//package com.lovo.testWebSocket;
//
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.Map;
//
///**
// * @author 王涛
// * 实现握手的类
// */
//@Component
//public class MyHandShake implements HandshakeInterceptor {
//    /**
//     * 握手之前干啥，常用来注册用户信息，绑定 WebSocketSession
//     * @param request
//     * @param response
//     * @param wsHandler
//     * @param attributes
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        return true;
//    }
//
//    /**
//     * 握手之后干啥
//     * @param request
//     * @param response
//     * @param wsHandler
//     * @param exception
//     */
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//
//    }
//}
