package com.lovo.util;

import com.lovo.service.IEventService;
import com.lovo.service.IResubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InjectServiceUtil {

    @Autowired
   private IEventService eventService;

    @Autowired
   private IResubmitService resubmitService;

    @PostConstruct
    public void init(){
        InjectServiceUtil.getInstance().eventService = this.eventService;
        InjectServiceUtil.getInstance().resubmitService = this.resubmitService;
    }

    /**
     *  实现单例 start
     */
    private static class SingletonHolder {
        private static final InjectServiceUtil INSTANCE = new InjectServiceUtil();
    }
    private InjectServiceUtil (){}
    public static final InjectServiceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     *  实现单例 end
     */
    public IEventService geteventService(){
        return InjectServiceUtil.getInstance().eventService;
    }
    public IResubmitService getresubmitService(){
        return InjectServiceUtil.getInstance().resubmitService;
    }


}
