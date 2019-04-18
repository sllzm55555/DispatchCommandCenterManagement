package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @RequestMapping("getToNoDealWith")
    public ModelAndView showNoDealWith(){
        ModelAndView mv=new ModelAndView("noDealWith");
        return mv;
    }
    @RequestMapping("getToDealWith")
    public ModelAndView showDealWith(){
        ModelAndView mv=new ModelAndView("dealWithIng");
        return mv;
    }

    @RequestMapping("goToDealWithIng")
    public String gotoDealWithIng(){
        return "dealWithIng";
    }
    @RequestMapping("goNoDealWithIng")
    public String gotoNoDealWithIng(){
        return "noDealWith";
    }

    @RequestMapping("goToNoDealWithDetails")
    public String gotoNoDealWithDetails(){
        return "noDealWithDetails";
    }


}
