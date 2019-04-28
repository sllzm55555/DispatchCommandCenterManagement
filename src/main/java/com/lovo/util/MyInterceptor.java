package com.lovo.util;

import com.lovo.dto.PowerDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean bl=false;
        //从session中获取权限对象
        List<PowerDto> list = (List<PowerDto>) httpServletRequest.getSession().getAttribute("powerList");
        if(null == list){
            //跳到登录页面
            //request.setAttribute("loginMessage", StringUtil.loginMessage);
            httpServletRequest.getRequestDispatcher("goToLogin").forward(httpServletRequest, httpServletResponse);

        }else {
            //获取访问的URI
            String uri = httpServletRequest.getServletPath();
            if(uri.contains("/goto")||uri.contains("/goTo")){
                for (PowerDto p : list) {
                    if(p.getPowerUri().equals(uri)){
                        bl=true;//找到了访问的uri
                        return bl;
                    }
                }
                //循环完都没有找到权限
                if(!bl){
                    //跳到错误页面
                    //request.setAttribute("loginMessage", StringUtil.loginMessage);
                    httpServletRequest.getRequestDispatcher("403").forward(httpServletRequest, httpServletResponse);
                }
            }else {
                return true;
            }

        }

        //判断URI是否权限对象中存在

        return bl;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        List<PowerDto> powerList = (List<PowerDto>) httpServletRequest.getSession().getAttribute("powerList");
//        String userName = powerList.get(0).getUserName();
//        modelAndView.addObject("userName", userName);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
