package com.lovo.controller;

import com.lovo.dto.*;
import com.lovo.entity.*;
import com.lovo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IAreaService areaService;

    @RequestMapping("callDept")
    public ModelAndView callDept(HttpServletRequest request){

        String resourceName[]=request.getParameterValues("resourceName");
        String renshu[]=request.getParameterValues("renshu");
        String cheliang[]=request.getParameterValues("cheliang");
        System.out.println(renshu.length);

        return null;
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

}
