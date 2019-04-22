package com.lovo.controller;


import com.lovo.entity.EventEntity;
import com.lovo.entity.EventPageBean;
import com.lovo.service.impl.EventServiceImpl;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EventJsonController {

    @Autowired
    EventServiceImpl eventService;
    @RequestMapping(value = "showEvent")
    public EventPageBean showList(String eventId, String eventType, String eventTime, int currPage){
        if (null==eventId){
            eventId="";
        }
        if (null==eventType){
            eventType="";
        }
        if (null==eventTime){
            eventTime="";
        }
        List<EventEntity> eventList = eventService.findEventEntitiesByCondition(eventId, eventType, eventTime, currPage, 1);
        int totalNumber = eventService.getTotalNumber(eventId, eventType, eventTime);
        EventPageBean page=new EventPageBean();
        page.setList(eventList);
        page.setCurrPage(currPage);
        page.setTotalPage(totalNumber);
        return page;
    }





}
