package com.lovo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dto.*;
import com.lovo.dto.out.EventSendDto;
import com.lovo.entity.*;
import com.lovo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 处理事件Controller
 */
@ServerEndpoint("/websocket")
@Controller
public class EventDealWithController {

    @Autowired
    private ISendResourceService sendResourceService;

    @Autowired
    private IEventService eventService;

    @Autowired
    private IResubmitService resubmitService;

    @Autowired
    private IPlanDeptService planDeptService;

    @Autowired
    private IPlanService planService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IAreaService areaService;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<EventDealWithController> webSocketSet = new CopyOnWriteArraySet<EventDealWithController>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @RequestMapping("callDept")
    public ModelAndView callDept(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();
        //确定事件
        String eventId = request.getParameter("eventId");
        String[] resourceName = request.getParameterValues("resourceName");
        String[] renshu = request.getParameterValues("renshu");
        String[] cheliang = request.getParameterValues("cheliang");
        String result = sendResourceService.callSendResource(eventId, resourceName, renshu, cheliang);
        modelAndView.addObject("eventId",eventId);
        modelAndView.addObject("result",result);
        modelAndView.setViewName("dealWithIngDetails");

        return modelAndView;
    }

    /**
     * @return 资源调用页面
     */
    @RequestMapping("sendResources")
    @ResponseBody
    public SendResourceBean sendResources(String eventId,Integer eventPeriod){

        SendResourceBean objectSendResourceBean = new SendResourceBean();
        //事件实体
        EventEntity eventEntity = eventService.findEventByEventId(eventId);
        ResubmitDto hotNewsResubmit = null;
        //预案相关
        List<PlanFindDto> planFindDtoList = new ArrayList<>();
        EventEntity entity = new EventEntity();
        //如果为1，则是未处理
        if (eventPeriod == 1) {
            //最后一次续报，有就显示这个，没有就是现实事件详情
            hotNewsResubmit = resubmitService.getHotNewsResubmit(eventId, eventPeriod, null);
            objectSendResourceBean.setResubmitDto(hotNewsResubmit);
        } else if (eventPeriod == 2) {
            //如果有最新未处理续报，则加入这条续报
            hotNewsResubmit = resubmitService.getHotNewsResubmit(eventId, eventPeriod, "1");
            if (hotNewsResubmit != null) {
                objectSendResourceBean.setResubmitDto(hotNewsResubmit);
                //显示最新续报的预案信息
                entity.setEventType(hotNewsResubmit.getEventType());
                entity.setEventLevel(hotNewsResubmit.getEventLevel());
            } else {
                entity.setEventType(eventEntity.getEventType());
                entity.setEventLevel(eventEntity.getEventLevel());
            }
            //如果有派遣信息，则加入
            List<SendResourcesDto> sendResourcesListByEventId = sendResourceService.getSendResourcesListByEventId(eventId);
            if (sendResourcesListByEventId != null || sendResourcesListByEventId.size() != 0) {
                objectSendResourceBean.setSendResourcesDtoList(sendResourcesListByEventId);
            }
        }
        //预案模板
        List<String> idByEnevTypeAndEnevLeve = planService.findAllEventIdByEnevTypeAndEnevLeve(entity.getEventType(), entity.getEventLevel());
        for (String s : idByEnevTypeAndEnevLeve) {
            PlanFindDto planFindDto = new PlanFindDto();
            PlanEntity planEntity = planService.findByPlanId(s);
            planFindDto.setPlanId(planEntity.getPlanId());
            planFindDto.setPlanName(planEntity.getPlanName());
            planFindDto.setPlanType(planEntity.getEnevType());
            planFindDto.setPlanLevel(planEntity.getEnevLeve());
            List<DeptDto> allDept = planService.getAllDept(planEntity.getPlanId());
            planFindDto.setDeptDtos(allDept);
            planFindDtoList.add(planFindDto);
        }
        objectSendResourceBean.setPlanFindDtoList(planFindDtoList);
        objectSendResourceBean.setEventEntity(eventEntity);

        return objectSendResourceBean;
    }

    /***
     * 资源调用页面跳转
     * @return
     */
    @RequestMapping("sendResourcesJump")
    public ModelAndView sendResourcesJump(String  eventId,String eventPeriod) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventId",eventId);
        modelAndView.addObject("eventPeriod",eventPeriod);
        modelAndView.setViewName("sendResources");
        return modelAndView;
    }

    /**
     * 选择预案
     * @param planId
     * @return
     */
    @RequestMapping("changePlan")
    @ResponseBody
    public List<DeptDto> changePlan(String planId) {
        List<DeptDto> allDept = planService.getAllDept(planId);
        return allDept;
    }

    @RequestMapping("changeLevel")
    @ResponseBody
    public List<DeptDto> changeLevel(String eventLevel,String eventType) {
        List<DeptDto> allDept = planService.getAllByLeveLAndType(eventLevel, eventType);
        return allDept;
    }

    @RequestMapping("choiceArea")
    @ResponseBody
    public AreaDto choiceArea(String area) {
        AreaDto areaDto = new AreaDto();
        AreaEntity areaEntity = areaService.findByAreaName(area);
        List<ResourceEntity> gonganList = resourceService.findAllByTypeAndAreaId("公安", areaEntity.getAreaId());
        List<ResourceEntity> xiaofangList = resourceService.findAllByTypeAndAreaId("消防", areaEntity.getAreaId());
        List<ResourceEntity> yiyuanList = resourceService.findAllByTypeAndAreaId("医院", areaEntity.getAreaId());
        areaDto.setGongan(gonganList);
        areaDto.setXiaofang(xiaofangList);
        areaDto.setYiyuan(yiyuanList);
        return areaDto;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
    }

    /**
     * MQ接受数据的方法
     *
     */
    @JmsListener(destination = "sendDispatchMessageToDispatchCommandCenterManagement")
    public void receiveQueue(String message) {
        //收到数据，保存数据库
        //将接收到的json转为对象
        EventSendDto eventSendDto = JSONObject.parseObject(message,EventSendDto.class);
        String eventId = eventSendDto.getId();
        EventEntity eventEntity = eventService.findEventByEventId(eventId);
        int n = sendResourceService.updateByEventEntity_EventIdAndRequestId(eventSendDto.getPerson().getPersonName(),eventSendDto.getPerson().getTel(), eventSendDto.getId(), eventSendDto.getRequestId());
        int m = sendResourceService.updateProgress(n,eventSendDto);

        System.out.println("保存结果为：" + m);
        this.onMessage(message,session);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message   客户端发送的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" + message);
        for (EventDealWithController item : webSocketSet) {
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
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
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

}
