package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/***
 * 事件页面跳转controller
 * @author lin
 */
@Controller
public class EventController {

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
    @RequestMapping("goNoDealWithIng")
    public String gotoNoDealWithIng(){
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

    /***
     * 资源派遣进度（每个人每个车的具体进度）
     * @return
     */
    @RequestMapping("scheduleOfResourceDispatch")
    public String gotoScheduleOfResourceDispatch() {
        return "scheduleOfResourceDispatch";
    }
}
