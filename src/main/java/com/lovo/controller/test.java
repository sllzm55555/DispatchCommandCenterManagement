package com.lovo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {



    public void sendMessage(String message){

    }
//    相当于一个监听器，只要开启以后，这个程序会一直运行，相当于死循环


    @RequestMapping("start")
    public String start(){
        return "ThymeleafModel";
    }

}
