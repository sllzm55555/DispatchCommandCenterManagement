package com.lovo.controller;

import com.lovo.entity.*;
import com.lovo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class LogController {

    @Autowired
    private IEventLogService eventLogService;

    @Autowired
    private IPlanLogService planLogService;
    /**
     * 每个分页显示8条数据
     */
    private Integer pageSize = 10;

    /**
     * 页面跳转 到事件操作日志
     * @return
     */
    @RequestMapping("goToLogPage")
    public ModelAndView goToLogPage(){
        ModelAndView mv = new ModelAndView("log");
        return mv;
    }

    /**
     * 存储事件操作日志
     * @return 返回一个ModelAndView
     */

    @RequestMapping("saveEventLog")
    public ModelAndView saveEventLog(){
        ModelAndView mv = new ModelAndView("log");
        String eLogId = "ffdg556";
        String operator = "高翔";
        String operateTime = "2019-04-24 12:30";
        int operateType = 2;
        String eventId = "123235334dftrhf";
        eventLogService.saveEventLog(eLogId,operator,operateTime, operateType, eventId);
        return mv;
    }
    /**
     * 多条件分页查询所有的事件操作日志
     * @param currpage
     * @param eventId
     * @param operator
     * @param operateTime
     * @param operateType
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("findEventLogByCondition")
    public PageBean findEventLogByCondition(Integer currpage,String eventId,
                                            String operator,
                                            String operateTime,
                                            String operateType) throws IOException {
        Integer operatetype = null;
        if(null==operateType){
            operatetype = null;
        }else if(operateType.contains("接受")){
            operatetype = 1;
        }else if(operateType.contains("处理")){
            operatetype = 2;
        }else{
            operatetype = null;
        }
        //页面集合,每页8条数据
        List<EventLogEntity> eventLogList = eventLogService.showEventLogByPage(currpage,pageSize,eventId,operatetype,operator,operateTime );

        //最大页数
        Integer totalPage = eventLogService.getCount(eventId,operatetype,
                operator,operateTime,pageSize);

        PageBean pageBean = new PageBean();

        pageBean.setTableBeans(eventLogList);

        pageBean.setCurrPate(currpage);

        pageBean.setTotalPate(totalPage);

        return pageBean;
    }

    /*-------------------------------预案管理----------------------------------*/

    /**
     * 跳转到预案操作日志界面
     */
    @RequestMapping("goToPlanLog")
    public ModelAndView goToPlanLogPage(){
        ModelAndView mv = new ModelAndView("planlog");

        return mv;
    }

    /**
     * 保存预案操作日志
     * @return
     */
    @RequestMapping("savePlanLog")
    public ModelAndView savePlanLog(){
        ModelAndView mv = new ModelAndView("log");
        String pLogId = "dffg";
        String operator = "高翔";
        String operateTime = "2019-02-16 12:30";
        int operateType = 1;
        String planId = "123235334dftrhf";
        planLogService.savePlanLog(pLogId, operator, operateTime, operateType, planId);
        return mv;
    }


    /**
     * 多条件分页查询所有满足条件的预案操作日志
     * @param currpage 当前页
     * @param planId 预案id
     * @param operator 操作者
     * @param operateTime 操作时间
     * @param operateType 操作类型
     * @return 返回pageBean
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("findPlanLogByCondition")
    public PageBean findPlanLogByCondition(Integer currpage,String planId,
                                            String operator,
                                            String operateTime,
                                            String operateType) throws IOException {
        Integer type = null;
        if(null==operateType){
            type = null;
        }else if(operateType.contains("添加")){
            type = 1;
        }else if(operateType.contains("修改")){
            type = 2;
        }else if(operateType.contains("删除")){
            type = 3;
        }else{
            type = null;
        }

        //页面集合,每页8条数据
        List<PlanLogEntity> planLogList = planLogService.showPlanLogByPage(currpage,pageSize,planId,type,operator,operateTime );
        //预案日志总页数
        Integer totalPage = planLogService.getCount(planId,type,operator,operateTime,pageSize);

        PageBean pageBean = new PageBean();

        pageBean.setTableBeans(planLogList);

        pageBean.setCurrPate(currpage);

        pageBean.setTotalPate(totalPage);

        return pageBean;
    }


    /*-----------------------测试方法--------------------------*//*
    @Autowired
    private IPlanService planService;
    @RequestMapping("testSavePlan")
    public ModelAndView testSavePlan(){
        ModelAndView mv = new ModelAndView("log");
        PlanEntity planEntity = new PlanEntity();
        planEntity.setPlanId("12345");
        planEntity.setPlanName("plan01");
        planEntity.setEnevLeve("dfsr");
        planEntity.setEnevType("地震");
        planEntity.setPlanDecs("dfhtfhdg");
        planService.savaPlan(planEntity);
        return mv;
    }

    @RequestMapping("testUpdatePlan")
    public ModelAndView testUpdatePlan(){
        ModelAndView mv = new ModelAndView("log");

        planService.updataPlanByPlanId("4028ab956a4e370e016a4e375be20000", "特别危险", "嗯，是真的!");
        return mv;
    }*/

    @Autowired
    private IPlanDeptService planDeptService;
    @RequestMapping("testDeletePlan")
    public ModelAndView testDeletePlan(){
        ModelAndView mv = new ModelAndView("log");
        planDeptService.deletePlanbyid("4028ab956a4e84c6016a4e87562a0000");
        return mv;
    }

    @Autowired
    private IEventService eventService;

    @RequestMapping("testSaveEvent")
    public ModelAndView testSaveEvent(){
        ModelAndView mv = new ModelAndView("log");
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEndTime("2019-09-09");
        eventEntity.setEventId("4");
        eventEntity.setEventType("teji");
        eventEntity.setEventUploadPeople("lin");
        eventService.saveEvent(eventEntity);
        return mv;
    }

    @RequestMapping("testUpdateEvent")
    public ModelAndView testUpdateEvent(){
        ModelAndView mv = new ModelAndView("log");

        eventService.updateEventData("1", 2);
        return mv;
    }

    @RequestMapping("testChangeEvent")
    public ModelAndView testChangeEvent(){
        ModelAndView mv = new ModelAndView("log");
        Date date = new Date();
        eventService.changeEventEndTime(date,  "1");
        return mv;
    }

}
