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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
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

    @Autowired
    RestTemplate restTemplate;

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
        int result = sendResourceService.callSendResource(eventId, resourceName, renshu, cheliang);
        if (result == 0) {
            modelAndView.addObject("eventId",eventId);
            modelAndView.setViewName("dealWithIngDetails");

            return modelAndView;
        } else {
            modelAndView.addObject("eventId",eventId);
            modelAndView.setViewName("scheduleOfResourceDispatch");
            return modelAndView;
        }
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

        //预案相关
        List<PlanFindDto> planFindDtoList = new ArrayList<>();
        String eventType = "";
        String eventLevel = "";
        //最后一次续报实体，有的话打印这个，没有就是现在事件的等级和类型
        ResubmitDto hotNewsResubmit = resubmitService.getHotNewsResubmit(eventId,eventPeriod,null);
        //如果为1，则是未处理
        if (hotNewsResubmit != null) {
            //最后一次续报，有就显示这个，没有就是现实事件详情
            eventType = hotNewsResubmit.getEventType();
            eventLevel = hotNewsResubmit.getEventLevel();
            objectSendResourceBean.setResubmitDto(hotNewsResubmit);
        } else  {
            eventType = eventEntity.getEventType();
            eventLevel = eventEntity.getEventLevel();
        }
        //如果有派遣信息，则加入
        List<SendResourcesDto> sendResourcesListByEventId = sendResourceService.getSendResourcesListByEventId(eventId);
        if (sendResourcesListByEventId != null) {
            objectSendResourceBean.setSendResourcesDtoList(sendResourcesListByEventId);
        }
        //预案模板
        List<String> idByEnevTypeAndEnevLeve = planService.findAllEventIdByEnevTypeAndEnevLeve(eventType, eventLevel);
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
    @RequestMapping(value = "sendResourcesJump")
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


        //远程调用资源方的方法，得到每个单位的可用人数并设置进list中
        /*if(gonganList != null){
            for (int i = 0; i < gonganList.size(); i++) {
                String url = gonganList.get(i).getUrl();
                String body = restTemplate.getForEntity("http://" + url + "/getResourceStatisticsJson", String.class).getBody();
                ResourceStatisticsDto resourceStatisticsDto = JSONObject.parseObject(body, ResourceStatisticsDto.class);
                gonganList.get(i).setPnumber(Integer.parseInt(resourceStatisticsDto.getPRescuingNum()));
                gonganList.get(i).setCnumber(Integer.parseInt(resourceStatisticsDto.getCVacantNum()));
            }
        }
        if(xiaofangList != null){
            for (int i = 0; i < xiaofangList.size(); i++) {
                String url = xiaofangList.get(i).getUrl();
                String body = restTemplate.getForEntity("http://" + url + "/getResourceStatisticsJson", String.class).getBody();
                ResourceStatisticsDto resourceStatisticsDto = JSONObject.parseObject(body, ResourceStatisticsDto.class);
                xiaofangList.get(i).setPnumber(Integer.parseInt(resourceStatisticsDto.getPRescuingNum()));
                xiaofangList.get(i).setCnumber(Integer.parseInt(resourceStatisticsDto.getCVacantNum()));
            }
        }
        if(yiyuanList != null){
            for (int i = 0; i < yiyuanList.size(); i++) {
                String url = yiyuanList.get(i).getUrl();
                String body = restTemplate.getForEntity("http://" + url + "/getResourceStatisticsJson", String.class).getBody();
                ResourceStatisticsDto resourceStatisticsDto = JSONObject.parseObject(body, ResourceStatisticsDto.class);
                yiyuanList.get(i).setPnumber(Integer.parseInt(resourceStatisticsDto.getPRescuingNum()));
                yiyuanList.get(i).setCnumber(Integer.parseInt(resourceStatisticsDto.getCVacantNum()));
            }
        }*/

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
//        EventEntity eventEntity = eventService.findEventByEventId(eventId);
        if(eventSendDto.getPerson()!= null){

            int n = sendResourceService.updateByEventEntity_EventIdAndRequestId(eventSendDto.getPerson().getPersonName(),
                    eventSendDto.getPerson().getTel(),
                    eventId,
                    eventSendDto.getRequestId());
        }
        int times = 0;
        if(eventSendDto.getPerson() != null){
            //派遣，需要添加派遣进度记录
            times = 1;
        }
        int m = sendResourceService.updateProgress(times,eventSendDto);

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
