package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class tttt {
    @RequestMapping("gotodem2")
    public ModelAndView gotodem1(){
        ModelAndView mv=new ModelAndView("demo1");
        return mv;
    }
}
