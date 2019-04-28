package com.lovo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lovo.entity.EventEntity;
import com.lovo.entity.PlanEntity;
import com.lovo.service.IEventLogService;
import com.lovo.service.IPlanLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 首先，这个@Aspect注释告诉Spring这是个切面类，
 * 然后@Compoment将转换成Spring容器中的bean或者是代理bean。
 * 总之要写切面这两个注解一起用就是了。
 *
 * author:刘金林
 */
@Aspect
@Component
public class LogAspect {
    /**
     * 当前操作者名字
     */
    private String userName = null;

    @Autowired
    private IPlanLogService planLogService;
    @Autowired
    private IEventLogService eventLogService;

    /**
     * 切入点描述 这个是service包的切入点
     * 切所有的事件操作的方法
     * eventServiceLog()签名，可以理解成这个切入点的一个名称
     */
    @Pointcut("execution(* com.lovo.service.*.login*(..))")
    public void loginServiceLog(){}

    /**
     * 如果有多個service的包需要切入的可以写多个
     *
     */
    @Pointcut("execution(public * com.lovo.service.*.*(..))")
    public void serviceLog(){}

    /**
     * 有多个的情况下可以切多个
     * @Before("controllerLog() || uiControllerLog()")
     * @param joinPoint 切入点
     *                  在切入点的方法run之前要干的
     */
    @Before("loginServiceLog()||serviceLog()")
    public void logBeforeController(JoinPoint joinPoint) {

        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        userName = (String)request.getSession().getAttribute("userName");

    }

    /**
     * 相当于监控所有的方法运行情况
     * 使用后置通知切到对操作的方法，并得到操作的参数
     * @param joinPoint
     * @param returnObj
     * operateType 代表操作类型，1：添加  2：修改    3：删除
     */

    @AfterReturning(returning = "returnObj", pointcut = "serviceLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnObj) {
        String methodName = joinPoint.getSignature().getName();
        /**
         * 操作类型
         */
        int operateType = 0;
        /**
         * 操作的预案或事件的id
         */
        String id = null;

        if(methodName.contains("save")){
            operateType = 1;
            if("savePlan".equals(methodName)){
                Object[] args1 = joinPoint.getArgs();
                PlanEntity planEntity = (PlanEntity)args1[0];
                id = planEntity.getPlanId();

                String operateTime = DateUtil.getNowTime();
                if(returnObj!=null){
                    planLogService.savePlanLog("savePlan"+id, userName, operateTime, operateType, id);
                }
            }else if("saveEvent".equals(methodName)){
                Object[] args1 = joinPoint.getArgs();
                EventEntity eventEntity = (EventEntity)args1[0];
                String operateTime = DateUtil.getNowTime();
                id = eventEntity.getEventId();
                if(returnObj!=null){
                    eventLogService.saveEventLog("saveEvent"+id, userName, operateTime, operateType, id);
                }
            }

        } else if(methodName.contains("updatePlan")){
            operateType = 2;
            Object[] args1 = joinPoint.getArgs();
            id = (String)args1[0];
            String operateTime = DateUtil.getNowTime();
            Integer flag = (Integer)returnObj;
            if(flag>=1){
                planLogService.savePlanLog("updatePlan"+id,userName,operateTime, operateType, id);
            }
        }else if(methodName.contains("changeEvent")){
            operateType = 2;
            Object[] args1 = joinPoint.getArgs();
            id = (String)args1[1];
            String operateTime = DateUtil.getNowTime();
            Integer flag = (Integer)returnObj;
            if(flag>=1){
                eventLogService.saveEventLog("updateEvent"+id,userName,operateTime, operateType, id);
            }
        }else if(methodName.contains("updateEvent")){
            operateType = 2;
            Object[] args1 = joinPoint.getArgs();
            id = (String)args1[0];
            String operateTime = DateUtil.getNowTime();
            Integer flag = (Integer)returnObj;
            if(flag>=1){
                eventLogService.saveEventLog("updateEvent"+id,userName,operateTime, operateType, id);
            }

        }else if(methodName.contains("deletePlan")){
            operateType = 3;
            Object[] args1 = joinPoint.getArgs();
            id = (String)args1[0];
            String operateTime = DateUtil.getNowTime();
            Integer f = (Integer)returnObj;
            if(f>=1){
                planLogService.savePlanLog("deletePlan"+id,userName,operateTime, operateType, id);
            }
        }else if(methodName.contains("deleteEvent")){
            operateType = 3;
            Object[] args1 = joinPoint.getArgs();
            id = (String)args1[0];
            String operateTime = DateUtil.getNowTime();
            if(returnObj!=null){
                eventLogService.saveEventLog("deleteEvent"+id,userName,operateTime, operateType, id);
            }
        }


//        List<Object> args = Arrays.asList(joinPoint.getArgs());



//        Object[] args1 = joinPoint.getArgs();
//        for (Object obj:args1) {
//            if(obj!=null){
//                System.out.println("参数有："+obj.toString());
//            }
//
//        }
    }




}