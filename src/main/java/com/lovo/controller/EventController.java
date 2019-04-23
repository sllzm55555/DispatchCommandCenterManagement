package com.lovo.controller;

import com.lovo.dto.ResubmitDto;
import com.lovo.entity.EventEntity;
import com.lovo.entity.EventPageBean;
import com.lovo.entity.PageBean;
import com.lovo.entity.ResubmitEntity;
import com.lovo.service.impl.EventServiceImpl;
import com.lovo.service.impl.ResubmitServiceImpl;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 * 事件页面跳转controller
 * @author lin
 */
@Controller
public class EventController {

    @Autowired
    EventServiceImpl  eventService;

    @Autowired
    ResubmitServiceImpl resubmitService;
    /**
     *  处理中事件首页
     * @return
     */
    @RequestMapping("goToDealWithIng")
    public String gotoDealWithIng(){
        return "dealWithIng";
    }

    /***
     * 未处理事件首页
     * @return
     */
    @RequestMapping("goNoDealWith")
    public String gotoNoDealWithIng(){
        ModelAndView mv=new ModelAndView("noDealWith");
        return "noDealWith";
    }

    /***
     * 未处理事件详情
     * @return
     */
    @RequestMapping("goToNoDealWithDetails")
    public String gotoNoDealWithDetails(){
        return "noDealWithDetails";
    }

    /**
     * 处理完成事件
     */
    @RequestMapping("gotoDealWithEd")
    public String gotoDealWithEd(){
        return "dealWithEd";
    }

    /**
     * 处理完成事件详情
     */
    @RequestMapping("gotoDealWithEdDetails")
    public String gotoDealWithEdDetails(){
        return "dealWithEdDetails";
    }

    /***
     * 资源派遣进度（每个人每个车的具体进度）
     * @return
     */
    @RequestMapping("scheduleOfResourceDispatch")
    public String gotoScheduleOfResourceDispatch() {
        return "scheduleOfResourceDispatch";
    }

    /**
     * @return 资源调用页面
     */
    @RequestMapping("goTosendResources")
    public String gotoSendResources(){
        return "sendResources";
    }

    /**
     *
     * @return  处理中事件查看详情
     */
    @RequestMapping("dealWithIngDetails")
    public String gotoDealWithIngDetails(){
        return "dealWithIngDetails";
    }

    /**
     * 中转 跳转到详情页面之前先把id信息存在session仓库里
     * @param eventId 事件主键的Id
     * @param request
     * @return 返回一个页面
     */
    @RequestMapping("transfer")
    public String showDealWith(String eventId,HttpServletRequest request){
        request.getSession().setAttribute("id",eventId);
        return "noDealWithDetails";
    }

    /**
     * 详情页面通过访问ajax到这里得到所有的数据
     * @param request
     * @param currPage 当前页码
     * @return 返回所有的数据
     */
    @RequestMapping("getAllData")
    @ResponseBody
    public PageBean showPageBean(HttpServletRequest request,int currPage,int eventPeriod){
        String id = (String)request.getSession().getAttribute("id");
        List<ResubmitDto> rList = resubmitService.findResourceEntitiesByEventEntityId(id, currPage, 1,eventPeriod);
        int totalPage = resubmitService.getAllResourNumber(id,eventPeriod);
        PageBean page=new PageBean();
        EventEntity event = eventService.findEventByEventId(id);
        page.setCurrPate(currPage);
        page.setTotalPate(totalPage);
        page.setTableBeans(rList);
        page.setObj(event);
        return page;
    }
    @RequestMapping("showEvent")
    @ResponseBody
    public EventPageBean showList(String eventId, String eventType, String eventTime, int currPage,int eventPeriod){
        if (null==eventId){
            eventId="";
        }
        if (null==eventType){
            eventType="";
        }
        if (null==eventTime){
            eventTime="";
        }
        List<EventEntity> eventList = eventService.findEventEntitiesByCondition(eventId, eventType, eventTime, currPage, 1,eventPeriod);
        int totalNumber = eventService.getTotalNumber(eventId, eventType, eventTime,eventPeriod);
        EventPageBean page=new EventPageBean();
        page.setList(eventList);
        page.setCurrPage(currPage);
        page.setTotalPage(totalNumber);

        return page;
    }

    /**
     * 从未处理事件的详情页面跳转到这里，
     * 得到未处理事件的所有信息，并封装好，跳转到资源调用的页面
     * @return 返回一个ModelAndView
     */

    @RequestMapping("getEventData")
    public ModelAndView getEventData(HttpServletRequest request){
        String id = (String) request.getSession().getAttribute("id");
        EventEntity event = eventService.findEventByEventId(id);
        return null;
    }

    /**
     * 跳转到处理中事件的详情页面之前的中转，先把要查询的事件的Id,存储到session仓库里面
     * @param eventId 事件的id
     * @return 返回到处理中事件详情的页面
     */
    @RequestMapping("jump")
    public ModelAndView getDealWithIngEventId(String eventId){
      ModelAndView mv=new ModelAndView("dealWithIngDetails");
      mv.addObject("eventId",eventId);
       return  mv;
    }


    @RequestMapping("getDealWithIngEventDeatils")
    public PageBean getEventAboutDealWithIng(String eventId,int eventPeriod){

        return null;
    }

}

