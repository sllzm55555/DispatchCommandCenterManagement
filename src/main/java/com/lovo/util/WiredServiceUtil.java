package com.lovo.util;

import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class WiredServiceUtil {
    @Autowired
    private ISendResourceService sendResourceService;

    @PostConstruct
    public void init(){
        WiredServiceUtil.getInstance().sendResourceService = this.sendResourceService;
    }

    /**
     *  实现单例 start
     */
    private static class SingletonHolder {
        private static final WiredServiceUtil INSTANCE = new WiredServiceUtil();
    }
    private WiredServiceUtil (){}
    public static final WiredServiceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     *  实现单例 end
     */
    public ISendResourceService getSendResourceService(){
        return WiredServiceUtil.getInstance().sendResourceService;
    }

}
