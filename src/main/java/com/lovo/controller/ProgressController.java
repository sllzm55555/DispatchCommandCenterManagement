package com.lovo.controller;

import com.lovo.dto.ProgressDto;
import com.lovo.dto.SendResourceDto;
import com.lovo.service.ISendProgressService;
import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进度跳转controller
 * @author lin
 * @date
 */
@Controller
public class ProgressController {
    @Autowired
    private ISendResourceService sendResourceService;

    @Autowired
    private ISendProgressService sendProgressService;

    /***
     * 资源派遣进度（每个人每个车的具体进度）
     * @return
     */
    @RequestMapping("progress")
    public String gotoScheduleOfResourceDispatch() {
        return "scheduleOfResourceDispatch";
    }

    @RequestMapping("showProgressByEventId")
    public ModelAndView showProgressByEventId(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        String eventId = request.getParameter("eventId");
        List<ProgressDto> allProgressDto = sendProgressService.findAllProgressDto(eventId);
        List<SendResourceDto> allSendResourceByEventId = sendResourceService.findAllSendResourceByEventId(eventId);
        modelAndView.addObject("allProgressDto",allProgressDto);
        modelAndView.addObject("allSendResourceByEventId",allSendResourceByEventId);

        modelAndView.setViewName("scheduleOfResourceDispatch");

        return modelAndView;
    }
}
