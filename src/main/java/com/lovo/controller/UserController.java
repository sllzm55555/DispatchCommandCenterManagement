package com.lovo.controller;


import com.lovo.entity.UserEntity;
import com.lovo.service.UserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("goToLogin")
    public String goToLogin(){

        return "login";
    }
    @RequestMapping("login")
    public ModelAndView login(UserEntity userEntity, HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = userService.login(userEntity.getUsername(),userEntity.getPassword());

        if (user != null){
            modelAndView.setViewName("dealWithIng");
            session.setAttribute("user",user);
        }else {
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping("checkUsernameExist")
    @ResponseBody
    public UserEntity checkUsernameExist(UserEntity user){
        UserEntity userEntity = userService.checkUsernameExist(user.getUsername());
        if (userEntity == null){
            userEntity = new UserEntity();
            userEntity.setUsername("用户名不存在");
        }
        return userEntity;
    }
    @RequestMapping("register")
    public ModelAndView register(UserEntity user){
        ModelAndView modelAndView = new ModelAndView("login");
        userService.register(user);
        return modelAndView;
    }
}
