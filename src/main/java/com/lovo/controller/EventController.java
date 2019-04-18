package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @RequestMapping("goToNoDealWith")
    public ModelAndView showNoDealWith(){
        ModelAndView mv=new ModelAndView("noDealWith");
        return mv;
    }
    @RequestMapping("goToDealWithIng")
    public ModelAndView showDealWith(){
        ModelAndView mv=new ModelAndView("dealWithIng");
        return mv;
    }
    @RequestMapping("goToDealWithEd")
    public ModelAndView showDealWithEds(){
        ModelAndView mv=new ModelAndView("dealWithEd");
        return mv;
    }

//    @RequestMapping("goToDealWithIng")
//    public String goToDealWithIng(){
//        return "dealWithIng";
//    }
//    @RequestMapping("goNoDealWith")
//    public String gotoNoDealWithIng(){
//        return "noDealWith";
//    }
//    @RequestMapping("goToDealWithEd")
//    public String showDealWithEd(){
//        return "dealWithEd";
//    }

    @RequestMapping("goToNoDealWithDetails")
    public String gotoNoDealWithDetails(){
        return "noDealWithDetails";
    }


}
