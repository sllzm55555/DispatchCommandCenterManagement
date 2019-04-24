package com.lovo.controller;

import com.lovo.dto.SendResourcesDto;
import com.lovo.entity.EventEntity;
import com.lovo.entity.PlanDeptEntity;
import com.lovo.entity.ResubmitEntity;
import com.lovo.entity.SendResourceBean;
import com.lovo.service.IEventService;
import com.lovo.service.IPlanDeptService;
import com.lovo.service.IResubmitService;
import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    /**
     * @return 资源调用页面
     */
    @RequestMapping("sendResources")
    @ResponseBody
    public SendResourceBean sendResources(String eventId){

        SendResourceBean objectSendResourceBean = new SendResourceBean();

        //事件实体 EventEntity eventEntity;
        EventEntity eventEntity = eventService.findEventByEventId(eventId);
        //最后一次续报实体 ResubmitEntity resubmitEntity;
//        ResubmitEntity resubmitEntity = resubmitService. ;
        //如果是续报，需要显示之前派遣的信息 List<T> SendResourcesList;
        List<SendResourcesDto> sendResourcesListByEventId = sendResourceService.getSendResourcesListByEventId(eventId);
        //预案模板 List<T> planDeptList;
        List<PlanDeptEntity> planDeptEntitiesByPlan = planDeptService.getPlanDeptEntitiesByPlan(eventEntity.getEventType(), eventEntity.getEventLevel());

        objectSendResourceBean.setEventEntity(eventEntity);
//        objectSendResourceBean.setResubmitEntity(resubmitEntity);
        objectSendResourceBean.setSendResourcesDtoList(sendResourcesListByEventId);
        objectSendResourceBean.setPlanDeptList(planDeptEntitiesByPlan);

        //假数据如果是续派的话要查询前面的派遣记录
//        SendResourcesDto sendResourcesDto = new SendResourcesDto();
//        sendResourcesDto.setTime(1);
//        sendResourcesDto.setDeptTypeNature("110");
//        sendResourcesDto.setNaturePolulation(4);
//        sendResourcesDto.setNatureResource(2);
//        sendResourcesDto.setDeptTypeHospital("120");
//        sendResourcesDto.setHospitalPolulation(4);
//        sendResourcesDto.setHospitalResource(2);

        return objectSendResourceBean;
    }

    /***
     * 资源调用页面跳转
     * @return
     */
    @RequestMapping("sendResourcesJump")
    public ModelAndView sendResourcesJump(String  eventId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventId",eventId);
        modelAndView.setViewName("sendResources");
        return modelAndView;
    }

}
