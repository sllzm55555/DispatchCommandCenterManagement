package com.lovo.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component //首先，这个@Aspect注释告诉Spring这是个切面类，然后@Compoment将转换成Spring容器中的bean或者是代理bean。 总之要写切面这两个注解一起用就是了。
public class EventLogAspect {
    //
    private final Logger logger = LoggerFactory.getLogger(EventLogAspect.class);

    /**
     * 切入点描述 这个是controller包的切入点
     */
    @Pointcut("execution(* com.lovo.service.*.saveEvent*(..))")
    public void controllerLog(){}//签名，可以理解成这个切入点的一个名称

    //如果有多個controller,service的包可以再写一个
    //@Pointcut("execution(public * com.stuPayment.uiController..*.*(..))")//切入点描述，这个是uiController包的切入点
    //public void uiControllerLog(){}
    //有多个的情况下可以切多个
    //@Before("controllerLog() || uiControllerLog()")

    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint) {

        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        // 记录下请求内容
        logger.info("################URL : " + request.getRequestURL().toString());
        logger.info("################HTTP_METHOD : " + request.getMethod());
        logger.info("################IP : " + request.getRemoteAddr());
        logger.info("################THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));

        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        logger.info("################CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //logger.info("################TARGET: " + joinPoint.getTarget());//返回的是需要加强的目标类的对象
        //logger.info("################THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象

    }
    @AfterReturning(returning = "returnObj", pointcut = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnObj) {
        System.out.println("#####################方法名 : " + returnObj.toString());
        String methodName = joinPoint.getSignature().getName();
        Object[] args1 = joinPoint.getArgs();
        for (Object obj:args1) {
            System.out.println(obj.toString());
        }
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("连接点方法为：" + methodName + ",参数为：" + args );
        //System.out.println("使用的方法有"+joinPoint);


    }



}