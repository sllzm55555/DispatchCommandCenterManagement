package com.lovo.controller;

import com.lovo.dto.DeptDto;
import com.lovo.dto.PlanFindDto;
import com.lovo.dto.ResubmitDto;
import com.lovo.dto.SendResourcesDto;
import com.lovo.entity.*;
import com.lovo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理事件Controller
 */
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

    /**
     * @return 资源调用页面
     */
    @RequestMapping("sendResources")
    @ResponseBody
    public SendResourceBean sendResources(String eventId){

        SendResourceBean objectSendResourceBean = new SendResourceBean();

        //事件实体 EventEntity eventEntity;
        EventEntity eventEntity = eventService.findEventByEventId(eventId);
        //最后一次续报实体
        ResubmitDto hotNewsResubmit = resubmitService.getHotNewsResubmit("1", 1, null);
        //如果是续报，需要显示之前派遣的信息 List<T> SendResourcesList;
        List<SendResourcesDto> sendResourcesListByEventId = sendResourceService.getSendResourcesListByEventId(eventId);
        //预案模板 List<T> PlanFindDto;
        List<PlanFindDto> planFindDtoList = new ArrayList<>();
        List<String> idByEnevTypeAndEnevLeve = planService.findAllEventIdByEnevTypeAndEnevLeve(eventEntity.getEventType(), eventEntity.getEventLevel());
        for (String s:idByEnevTypeAndEnevLeve) {
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
        objectSendResourceBean.setEventEntity(eventEntity);
        objectSendResourceBean.setResubmitDto(hotNewsResubmit);
        objectSendResourceBean.setSendResourcesDtoList(sendResourcesListByEventId);
        objectSendResourceBean.setPlanFindDtoList(planFindDtoList);

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

    @RequestMapping("changePlan")
    @ResponseBody
    public List<DeptDto> changePlan(String planId) {
        List<DeptDto> allDept = planService.getAllDept(planId);
        return allDept;
    }

}
