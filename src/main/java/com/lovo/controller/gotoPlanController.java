package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class gotoPlanController {
    /**
     * 跳转预案主页
     * @return
     */
    @RequestMapping("gotoPlan")
    public ModelAndView gotoPlan(){
        ModelAndView mv=new ModelAndView("plan");
        return mv;
    }

    /**
     * 跳转添加预案页面
     * @return
     */
    @RequestMapping("gotoaddPlan")
    public ModelAndView gotoaddPlan(){
        ModelAndView mv=new ModelAndView("addplan");
        return mv;
    }
}
