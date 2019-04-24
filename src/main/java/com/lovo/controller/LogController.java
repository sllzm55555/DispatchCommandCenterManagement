package com.lovo.controller;

import com.lovo.entity.EventLogEntity;
import com.lovo.entity.PageBean;
import com.lovo.entity.PlanLogEntity;
import com.lovo.service.IEventLogService;
import com.lovo.service.IPlanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
    private Integer pageSize = 8;

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
        String eLogId = "wart1020999";
        String operator = "堂中原";
        String operateTime = "2019-04-16 12:30";
        int operateType = 1;
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
                                            Integer operateType) throws IOException {
        //页面集合,每页8条数据
        List<EventLogEntity> eventLogList = eventLogService.showEventLogByPage(currpage,pageSize,eventId,operateType,operator,operateTime );

        //最大页数
        Integer totalPage = eventLogService.getCount(eventId,operateType,
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
        String pLogId = "yuan01";
        String operator = "黄中原";
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
                                            Integer operateType) throws IOException {
        //页面集合,每页8条数据
        List<PlanLogEntity> planLogList = planLogService.showPlanLogByPage(currpage,pageSize,planId,operateType,operator,operateTime );
        //预案日志总页数
        Integer totalPage = planLogService.getCount(planId,operateType,operator,operateTime,pageSize);

        PageBean pageBean = new PageBean();

        pageBean.setTableBeans(planLogList);

        pageBean.setCurrPate(currpage);

        pageBean.setTotalPate(totalPage);

        return pageBean;
    }
}
