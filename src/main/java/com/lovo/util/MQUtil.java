package com.lovo.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class MQUtil {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMQ(Object data){
        jmsTemplate.send("send", new MessageCreator() {
            @Override
            public TextMessage createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                String strJson = JSONObject.toJSONString(data);
                textMessage.setText(strJson);
                return textMessage;
            }
        });
    }


    public void testSendMQ(Object data){
        jmsTemplate.send("testSend", new MessageCreator() {
            @Override
            public TextMessage createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                String strJson = JSONObject.toJSONString(data);
                textMessage.setText(strJson);
                return textMessage;
            }
        });
    }

    public void sendEventSinkDto(String str,Object data){
        jmsTemplate.send(str, new MessageCreator() {
            @Override
            public TextMessage createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                String strJson = JSONObject.toJSONString(data);
                textMessage.setText(strJson);
                return textMessage;
            }
        });
    }
    public void sendEvent(Object data){
        jmsTemplate.send("eventNodealWith", new MessageCreator() {
            @Override
            public TextMessage createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                String strJson = JSONObject.toJSONString(data);
                textMessage.setText(strJson);
                return textMessage;
            }
        });
    }

    public void sendResubmit(Object data){
        jmsTemplate.send("resubitDto", new MessageCreator() {
            @Override
            public TextMessage createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                String strJson = JSONObject.toJSONString(data);
                textMessage.setText(strJson);
                return textMessage;
            }
        });
    }

}
