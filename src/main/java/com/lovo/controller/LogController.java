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

    @Autowired
    private IEventLogDao eventLogDao;

    @RequestMapping("goToLogPage")
    public ModelAndView goToLogPage(){
        ModelAndView mv = new ModelAndView("log/log");
        Pageable pageable = new PageRequest(0, 2);
        List<EventLogEntity> eventLogList = eventLogService.showEventLogListPage(pageable, "", 0, "zhangsan", "");
        mv.addObject("eventLogList", eventLogList);
        return mv;
    }

    @RequestMapping("saveEventLog")
    public ModelAndView saveEventLog(){
        ModelAndView mv = new ModelAndView("log/log");
        EventLogEntity eventLogEntity = new EventLogEntity();
        eventLogEntity.seteLogId("cao1020923");
        eventLogEntity.setOperateType(1);
        eventLogEntity.setOperator("qinghao以通过");
        eventLogEntity.setOperateTime("2019-08-05 15:30");
        eventLogEntity.setEventId("huozai0001");
        eventLogDao.save(eventLogEntity);

        return mv;
    }


}
