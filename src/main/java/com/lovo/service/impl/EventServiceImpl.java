package com.lovo.service.impl;

import com.lovo.dao.IEventDao;
import com.lovo.entity.EventEntity;
import com.lovo.service.IEventService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 事件的业务实现层
 * @author 阿枫
 * @date 2019-04-19
 */
@Service(value = "eventService")
public class EventServiceImpl implements IEventService {

    @Autowired
    private IEventDao eventDao;
    @Transactional
    @Override
    public List<EventEntity> findEventEntitiesByCondition(String eventId, String eventType, String eventTime, int pageNo, int pageSize,int eventPeriod ) {

        if (null==eventId||"".equals(eventId)){
            eventId=null;
        }
        if (null==eventType||"".equals(eventType)){
            eventType=null;
        }
        if (null==eventTime||"".equals(eventTime)){
            eventTime=null;
        }

        pageNo=pageSize*(pageNo-1);
        return  eventDao.findEventEntitiesByCondition(eventId,eventType,eventTime,pageNo,pageSize,eventPeriod);
    }

    @Override
    public int getTotalNumber(String eventId, String eventType, String eventTime,int eventPeriod) {
        if (null==eventId||"".equals(eventId)){
            eventId=null;
        }
        if (null==eventType||"".equals(eventType)){
            eventType=null;
        }
        if (null==eventTime||"".equals(eventTime)){
            eventTime=null;
        }
        List<EventEntity> all = eventDao.findAllEventEntitiesNumberByCondition(eventId, eventType, eventTime,eventPeriod);
       int totalpage=0;
       totalpage=all.size();
       totalpage=totalpage%1==0?(totalpage/1):(totalpage/1+1);
        return totalpage;
    }

    @Override
    public EventEntity findEventByEventId(String eventId) {
        return eventDao.findEventEntityByEventId(eventId);
    }


    @Override
    @Transactional
    public void changeEventPeriod(String eventId) {
        eventDao.changeEventPeriod(eventId);
    }

    @Override
    @Transactional
    public void changeEventEndTime(Date date, String eventId) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        eventDao.changeDate(format, eventId);
    }
}
