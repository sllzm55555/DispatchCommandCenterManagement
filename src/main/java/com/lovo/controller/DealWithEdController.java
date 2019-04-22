package com.lovo.controller;

import com.lovo.entity.EventEntity;
import com.lovo.entity.PageBean;
import com.lovo.service.IDealWithEdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DealWithEdController {

    @Autowired
    private IDealWithEdService dealWithEdService;

    @ResponseBody
    @RequestMapping("changePage")
    public PageBean changePage(int pageNum, EventEntity event){
        List<EventEntity> list = dealWithEdService.findAll(pageNum,event);
        int pageAll = dealWithEdService.pageAll(pageNum,event);
        PageBean<EventEntity> pageBean = new PageBean<>();
        pageBean.setCurrPate(pageNum);
        pageBean.setTableBeans(list);
        pageBean.setTotalPate(pageAll);
        return pageBean;
    }

    @RequestMapping("goToPageDealWithEd")
    public String goToPageDealWithEd(){

        return "dealWithEd";
    }

    @RequestMapping("check")
    public ModelAndView check(String eventId){
        ModelAndView modelAndView = new ModelAndView("dealWithEdDetails");

        EventEntity event = dealWithEdService.findDealWithEventById(eventId);
        modelAndView.addObject("event",event);
        return modelAndView;
    }


}
