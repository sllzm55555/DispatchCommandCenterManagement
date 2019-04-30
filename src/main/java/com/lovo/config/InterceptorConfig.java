package com.lovo.config;

import com.lovo.util.MyInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    //添加多个拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/login")
                .excludePathPatterns("/testLogin")
                .excludePathPatterns("/goToLogin").excludePathPatterns("/error").excludePathPatterns("/goTosendEvent");}

    /**
     * 放过静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/staticResources/**")
                .addResourceLocations("classPath:/staticResources/");

        registry.addResourceHandler("/templates/**")//放行的资源
                .addResourceLocations("classpath:/templates/"); //资源所在本地位置
    }
}
