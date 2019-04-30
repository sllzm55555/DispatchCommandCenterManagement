package com.lovo.controller;


import com.lovo.dto.PowerDto;
import com.lovo.dto.PowerDtoReslut;
import com.lovo.entity.UserEntity;
import com.lovo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("goToLogin")
    public String goToLogin(){

        return "login";
    }

//    @RequestMapping("login")
//    public ModelAndView login(UserEntity userEntity, HttpSession session){
//
//        ModelAndView modelAndView = new ModelAndView();
//        UserEntity user = userService.login(userEntity.getUsername(),userEntity.getPassword());
//
//        if (user != null){
//            modelAndView.setViewName("dealWithIng");
//            session.setAttribute("user",user);
//        }else {
//            modelAndView.setViewName("login");
//        }
//
//        return modelAndView;
//    }

    @RequestMapping("login")
    public ModelAndView testLogin(String username, String password, HttpServletRequest rq){
        ModelAndView mv=new ModelAndView("dealWithIng");
        PowerDtoReslut pdresult = restTemplate.getForEntity("http://PremessionManagement/{userName}/{password}/PowerDtoReslut", PowerDtoReslut.class,username,password).getBody();
        //用户信息
        List<PowerDto> list = pdresult.getDto();

        if(null==list){
            //完全得不到该用户的信息
            //用户名或密码错误,跳转到
            //mv.addObject("loginMessage", StringUtil.loginMessage);
            mv.setViewName("login");
        }else{
            //登录成功就把用户信息放入到session
            rq.getSession().setAttribute("powerList", list);
            rq.getSession().setAttribute("userName", list.get(0).getUserName());

        }
        //远程调用用户权限
        return mv;
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
        String userName = user.getUsername();
        String password = user.getPassword();
        ModelAndView modelAndView = new ModelAndView("login");
        userService.register(user);

        restTemplate.getForEntity("http://PremessionManagement/{userName}/{password}/userRegister", String.class,userName,password).getBody();
        return modelAndView;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @RequestMapping("403")
    public String noPower(){
        return "403";
    }

    @RequestMapping("serverCrash")
    public String serverCrash(){
        return "serverCrash";
    }
}
