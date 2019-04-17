package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {

    @RequestMapping("start")
    public String start(){
        return "ThymeleafModel";
    }

}
