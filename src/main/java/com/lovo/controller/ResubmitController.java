package com.lovo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dto.NeedResubmitDto;
import com.lovo.entity.EventEntity;
import com.lovo.entity.ResubmitEntity;
import com.lovo.service.impl.ResubmitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ResubmitWebsocket")
@Controller
public class ResubmitController {
    @Autowired
    ResubmitServiceImpl resubmitService;

    private static CopyOnWriteArraySet<ResubmitController> websocket=new CopyOnWriteArraySet<ResubmitController>();

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        websocket.add(this);
    }

    @JmsListener(destination = "resubitDto")
//    message就是传送过来的信息
    public  void receiveQueue6(String message) {
//        通过键在sessionMap集合里面得到WebSocketSession 对象

//        如果session 对象不为空并且是开启状态
        NeedResubmitDto rdto = JSONObject.parseObject(message, NeedResubmitDto.class);
        ResubmitEntity reb = new ResubmitEntity();
        reb.setReportId(rdto.getReportId());
        EventEntity e=new EventEntity();
        e.setEventId(rdto.getEventId());
        reb.setEventEntity(e);
        reb.setHurtPopulation(rdto.getHurtPopulation());
        reb.setEventLevel(rdto.getEventLevel());
        reb.setReportPeople(rdto.getReportPeople());
        reb.setReportPeriod("1");
        reb.setReportTime(rdto.getReportTime());
        reb.setReportDesc(rdto.getReportDesc());
        reb.setReportTel(rdto.getReportTel());
        resubmitService.saveResubmit(reb);
        this.onMessage(message,session);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message   客户端发送的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);
        for (ResubmitController item : websocket) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 实现服务器主动推送
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        websocket.remove(this);
        System.out.println("连接关闭");
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println( "发生错误" );
        error.printStackTrace();
    }

}
