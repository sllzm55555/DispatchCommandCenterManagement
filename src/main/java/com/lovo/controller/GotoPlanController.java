/**
 * 所有预案有关的跳转页
 */
package com.lovo.controller;

import com.lovo.service.IDeptService;
import com.lovo.service.IPlanDeptService;
import com.lovo.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GotoPlanController {
    @Autowired
    private IPlanService planService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IPlanDeptService planDeptService;
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
    @RequestMapping("addPlanhtml")
    public ModelAndView gotoaddPlan(){
        ModelAndView mv=new ModelAndView("addplan");
        return mv;
    }
    /**
     * 跳转详情页面
     * @return
     */
    @RequestMapping("findPlanhtml")
    public ModelAndView gotofindPlan(){
        ModelAndView mv=new ModelAndView("findPlan");
        return mv;
    }
}
