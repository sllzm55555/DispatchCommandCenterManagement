package com.lovo.activeMQ;
import javax.jms.Destination;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dao.IEventDao;
import com.lovo.dto.NoDealWithDto;
import com.lovo.entity.EventEntity;
import com.lovo.service.IEventService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王涛
 * 通过MQ传输消息的工具类
 */
@Component(value = "producer")
public class Producer{
    /**
     * 注入springboot封装的工具类
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    IEventDao eventDao;
    @Autowired
    IEventService eventService;
    /**
     * 队列的map集合，键为单位名，值为该单位的队列
     */
    private Map<String, Destination> destinationMap = new HashMap<>();
    /**
     * 将消息放入队列，传输到资源方
     * @param queueName 队列名
     * @param message 消息的内容，通常是转化为json格式的对象
     *                该对象通常是一个DTO，如果传输给资源方，那么Message就是一个包含续报list和单个单位的dto
     * @throws InterruptedException 传输异常
     */
    public void sendMessage(String queueName, String message) throws InterruptedException {

        Destination destination = null;
        EventEntity event =null;

        for (String key : destinationMap.keySet()) {
            if(key.equals(queueName)){
                destination = destinationMap.get(key);
                NoDealWithDto n = JSONObject.parseObject(message, NoDealWithDto.class);
                EventEntity e=new EventEntity();
                e.setEventName(n.getEventName());
                eventDao.save(e);
                break;
            }
        }
        if(destination == null){
            destination = new ActiveMQQueue(queueName);
            //拿到数据之后，封装成事件的对象，装到数据库，然后随便发送任意消息到页面，在页面上收到消息后
            // 使用ajax,执行回调函数
            if ("eventNodealWith".equals(queueName)){
                NoDealWithDto eventDto = JSONObject.parseObject(message, NoDealWithDto.class);
                event=new EventEntity();
                event.setEventName(eventDto.getEventName());
                event.setEventId(eventDto.getEventId());
                event.setHurtPopulation(eventDto.getHurtPopulation());
                event.setEventPeriod(eventDto.getEventPeriod());
                event.setEventLevel(eventDto.getEventLevel());
                event.setEventType(eventDto.getEventType());
                event.setEventUploadPeople(eventDto.getEventUploadPeople());
                event.setAlarmAddress(eventDto.getAlarmAddress());
                event.setEventTime(eventDto.getEventTime());
                event.setAlarmTel(eventDto.getAlarmTel());
                event.setEventArea(eventDto.getEventArea());
                event.setAlarmPerson(eventDto.getAlarmPerson());
                event.setUniqueAttr(eventDto.getUniqueAttr());
                event.setEndTime(null);
                eventService.saveEvent(event);

            }
            destinationMap.put(queueName, destination);

        }
        jmsTemplate.convertAndSend(destination, "a");
    }
}
