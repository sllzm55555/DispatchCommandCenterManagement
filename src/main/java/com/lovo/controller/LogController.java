package com.lovo.controller;

import com.lovo.dao.IEventLogDao;
import com.lovo.entity.EventLogEntity;
import com.lovo.service.IEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private IEventLogService eventLogService;



    @RequestMapping("goToLogPage")
    public ModelAndView goToLogPage(){
        ModelAndView mv = new ModelAndView("log");
        Pageable pageable = new PageRequest(0, 2);
        //List<EventLogEntity> eventLogList = eventLogService.showEventLogListPage( "", 0, "zhangsan", "",pageable);
        //mv.addObject("eventLogList", eventLogList);
        return mv;
    }

    @RequestMapping("saveEventLog")
    public ModelAndView saveEventLog(){
        ModelAndView mv = new ModelAndView("log");
        String eLogId = "wart1020999";
        String operator = "堂中原";
        String operateTime = "2019-04-16 12:30";
        int operateType = 1;
        String eventId = "123235334dftrhf";
        eventLogService.saveEventLog(eLogId,operator,operateTime, operateType, eventId);

        return mv;
    }

    @RequestMapping("getEventLog")
    public ModelAndView getEventLog(){
        ModelAndView mv = new ModelAndView("log");
        Pageable pageable = new PageRequest(1, 3);
        pageable.getPageNumber();
        String operator = null;
        List<EventLogEntity> eventLogList = eventLogService.getAllEventLogByPage(operator, pageable);

        mv.addObject("eventLogList", eventLogList);
        return mv;
    }

}
