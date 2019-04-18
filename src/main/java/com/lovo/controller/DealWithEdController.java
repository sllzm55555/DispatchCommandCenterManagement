package com.lovo.controller;

import com.lovo.entity.EventEntity;
import com.lovo.entity.UserEntity;
import com.lovo.service.IDealWithEdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DealWithEdController {

    @Autowired
    private IDealWithEdService dealWithEdService;

    @RequestMapping("showDealWithEdPage")
    public ModelAndView showDealWithEdPage(){
        ModelAndView modelAndView = new ModelAndView("dealWithEd");
        int pageNum = 0;
        List<EventEntity> list = dealWithEdService.showDealWithEdEventList(pageNum);
        int pageAll = dealWithEdService.getPageAll();
        modelAndView.addObject("list",list);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("pageAll",pageAll);
        return modelAndView;
    }

    @RequestMapping("changePage")
    public ModelAndView changePage(int pageNum){
        ModelAndView modelAndView = new ModelAndView("dealWithEd");
        List<EventEntity> list = dealWithEdService.showDealWithEdEventList(pageNum);
        int pageAll = dealWithEdService.getPageAll();
        modelAndView.addObject("list",list);
        modelAndView.addObject("pageAll",pageAll);
        modelAndView.addObject("pageNum",pageNum);
        return modelAndView;
    }


}
