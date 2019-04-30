package com.lovo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dto.NeedResubmitDto;
import com.lovo.dto.NoDealWithDto;
import com.lovo.dto.ResubmitDto;
import com.lovo.entity.EventEntity;
import com.lovo.entity.EventPageBean;
import com.lovo.entity.PageBean;
import com.lovo.entity.ResubmitEntity;
import com.lovo.service.impl.EventServiceImpl;
import com.lovo.service.impl.ResubmitServiceImpl;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 * 事件页面跳转controller
 * @author lin
 */
@ServerEndpoint("/EventWebSocket")
@Controller
public class EventController {

    @Autowired
    EventServiceImpl  eventService;

    @Autowired
    ResubmitServiceImpl resubmitService;

    private static CopyOnWriteArraySet<EventController> websocket=new CopyOnWriteArraySet<EventController>();

    private Session sessionn;


    @OnOpen
    public void onOpen(Session sessionn){
        this.sessionn=sessionn;
        websocket.add(this);
    }

    //    事件的监听器，destination参数的值是队列的名字
    @JmsListener(destination = "eventNodealWith")
//    message就是传送过来的信息
    public  void receiveQueue4(String message) {

                NoDealWithDto event = JSONObject.parseObject(message, NoDealWithDto.class);

                EventEntity e=new EventEntity();
                e.setEventId(event.getEventId());
                e.setEventName(event.getEventName());
                e.setHurtPopulation(event.getHurtPopulation());
                e.setUniqueAttr(event.getUniqueAttr());
                e.setAlarmPerson(event.getAlarmPerson());
                e.setEventArea(event.getEventArea());
                e.setAlarmTel(event.getAlarmTel());
                e.setEventTime(event.getEventTime());
                e.setAlarmAddress(event.getAlarmAddress());
                e.setEventPeriod(event.getEventPeriod());
                e.setEventUploadPeople(event.getEventUploadPeople());
                e.setEndTime(null);
                e.setEventType(event.getEventType());
                e.setEventLevel(event.getEventLevel());
                 eventService.saveEvent(e);
                this.onMessage(message,sessionn);

    }

    /**
     * 收到客户端消息后调用的方法
     * @param message   客户端发送的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);
        for (EventController item : websocket) {
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
        this.sessionn.getBasicRemote().sendText(message);
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
    @RequestMapping("goToNoDealWith")
    public String gotoNoDealWith(){
        ModelAndView mv=new ModelAndView("noDealWith");
        return "noDealWith";
    }

    /***
     * 未处理事件详情
     * @return
     */
    @RequestMapping("goNoDealWithDetails")
    public String gotoNoDealWithDetails(){
        return "noDealWithDetails";
    }

    /**
     * 处理完成事件
     */
    @RequestMapping("goToDealWithEd")
    public String gotoDealWithEd(){
        return "dealWithEd";
    }

    /**
     * 处理完成事件详情
     */
    @RequestMapping("goDealWithEdDetails")
    public String gotoDealWithEdDetails(){
        return "dealWithEdDetails";
    }

//    /***
//     * 资源派遣进度（每个人每个车的具体进度）
//     * @return
//     */
//    @RequestMapping("scheduleOfResourceDispatch")
//    public String gotoScheduleOfResourceDispatch() {
//        return "scheduleOfResourceDispatch";
//    }

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
    @RequestMapping("goDealWithIngDetails")
    public String gotoDealWithIngDetails(){
        return "dealWithIngDetails";
    }

    /**
     * 中转,把Id存储在ModelAndView中，在页面动态取值，进行业务处理
     * @param eventId 事件主键的Id
     * @return 返回ModelAndView
     */
    @RequestMapping("transfer")
    public ModelAndView  showDealWith(String eventId){
        ModelAndView mv=new ModelAndView("noDealWithDetails");
        mv.addObject("eventId",eventId);
        return mv;
    }



    /**
     * 详情页面通过访问ajax到这里得到所有的数据
     * @return 返回所有的数据
     */
    @RequestMapping("getAllData")
    @ResponseBody
    public PageBean showPageBean(String eventId,int eventPeriod,String reperiod){
        List<ResubmitDto> rList =null;
       List<ResubmitDto> allList =resubmitService.findAllResubmitListByIdAndPeriod(eventId,eventPeriod,reperiod);
        if (null!=allList){
        rList = allList;
        }
        PageBean page=new PageBean();
        EventEntity event = eventService.findEventByEventId(eventId);
        if (null!=rList){
        page.setTableBeans(rList);
        }
        page.setObj(event);
        return page;
    }

    /**
     * 带条件查询事件集合
     * @param eventId 事件id
     * @param eventType 事件类型
     * @param eventTime 事件时间
     * @param currPage 当前页码
     * @param eventPeriod 事件阶段
     * @return
     */
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
        List<EventEntity> eventList = eventService.findEventEntitiesByCondition(eventId, eventType, eventTime, currPage, 5,eventPeriod);
        int totalNumber = eventService.getTotalNumber(eventId, eventType, eventTime,eventPeriod);
        EventPageBean page=new EventPageBean();
        page.setList(eventList);
        page.setCurrPage(currPage);
        page.setTotalPage(totalNumber);
        return page;
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


    /**
     * 修改事件的进度，等级，伤亡人数，并且把所有的续报的状态改变
     * @param eId 事件的id
     * @param ePeriod 事件的进度
     * @return 返回页面
     */
    @RequestMapping("updateEvent")
    public String updateEventData(String eId,int ePeriod){
        String str="";
//        把当前事件的所有续报状态改成已处理
        resubmitService.changeResubmitPeriod(eId);
//        根据事件Id,事件的状态，续报的状态，改变这个事件的进度，等级，伤亡人数
        eventService.updateEventData(eId, ePeriod);
//   当事件的等级，受伤人数，事件等级全部改变以后，重定向到这个controller，看看从页面传过来的数据
        if (ePeriod==1){
//          看传过来的事件进度为1的话，不做任何操作，直接跳转到处理中事件的首页
            str="dealWithIng";
        }else{
//           传过来的数据如果不为1的话，就是处理中的事件，除了改变这些东西外，还要改变事件的结束时间
//            并且跳转到处理完事件的首页
            eventService.changeEventEndTime(new Date(),eId);
            str="dealWithEd";
        }
        return str;
    }




}

