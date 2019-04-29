package com.lovo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lovo.activeMQ.Producer;
import com.lovo.dto.NoDealWithDto;
import com.lovo.testDto.EventSinkDto;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author wt
 */
@Controller
public class WebSocketController {

    @Autowired
    Producer producer;

    @RequestMapping("websocket")
    public String websocket() {
        return "webSocket/websocket";
    }

    @RequestMapping("websocket02")
    public String websocket02() {
        return "webSocket/websocket02";
    }

    /**
     * 给三组发消息
     * @throws InterruptedException
     */
    @RequestMapping("sendMsg")
    @ResponseBody
    public void sendMsg() throws InterruptedException {

        EventSinkDto eventSinkDto = new EventSinkDto();

        eventSinkDto.setId("1");
        eventSinkDto.setAlarmAddress("郫县");
        eventSinkDto.setAlarmPerson("假老练");
        eventSinkDto.setAlarmTel("133232321");
        eventSinkDto.setCarNum(10);
        eventSinkDto.setPersonNum(10);
        eventSinkDto.setEventArea("郫县");
        eventSinkDto.setEventTime(new Timestamp(System.currentTimeMillis()));

        producer.sendMessage("yiyuan",JSONObject.toJSONString(eventSinkDto));



    }

    /**
     * 给一组发消息
     * @throws InterruptedException
     */
    @RequestMapping("sendMsg2")
    @ResponseBody
    public void sendMsg2() throws InterruptedException {
        String string = "123456";
        producer.sendMessage("sendMessageToUploadSystem", string);
    }
    @RequestMapping("sendEvent")
    @ResponseBody
    public void sendMsg3(){
        try {
//            {js}
            NoDealWithDto noDealWithDto = new NoDealWithDto();
            noDealWithDto.setEventId("10");
            noDealWithDto.setAlarmAddress("红瓦寺街道");
            noDealWithDto.setAlarmPerson("老赵");
            noDealWithDto.setAlarmTel("1232123122");
            noDealWithDto.setEventArea("武侯区");
            noDealWithDto.setEventLevel("1级");
            noDealWithDto.setEventName("小区火灾");
            noDealWithDto.setEventPeriod(1);
            noDealWithDto.setEventTime("2019-01-10 12:23:32");
            noDealWithDto.setEventUploadPeople("小李");
            noDealWithDto.setUniqueAttr("小区电路老化起火");
            noDealWithDto.setHurtPopulation(3);
            noDealWithDto.setEventType("火灾");
            String s = JSONObject.toJSONString(noDealWithDto);
            producer.sendMessage("eventNodealWith",s);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
