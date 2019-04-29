package com.lovo.controller;

import com.lovo.dao.ShowDto;
import com.lovo.dto.ProgressDto;
import com.lovo.dto.SendResourceDto;
import com.lovo.service.ISendProgressService;
import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView gotoScheduleOfResourceDispatch(String eventId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventId",eventId);
        modelAndView.setViewName("scheduleOfResourceDispatch");

        return modelAndView;
    }

    @RequestMapping("showProgressByEventId")
    @ResponseBody
    public ShowDto showProgressByEventId(String eventId) {

        List<ProgressDto> allProgressDto = sendProgressService.findAllProgressDto(eventId);
        List<SendResourceDto> allSendResourceByEventId = sendResourceService.findAllSendResourceByEventId(eventId);
        ShowDto showDto = new ShowDto();
        showDto.setProgressDtoList(allProgressDto);
        showDto.setSendResourceDtoList(allSendResourceByEventId);

        return showDto;
    }
}
