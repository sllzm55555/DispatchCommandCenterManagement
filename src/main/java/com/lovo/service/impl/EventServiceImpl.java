package com.lovo.service.impl;

import com.lovo.dao.IEventDao;
import com.lovo.entity.EventEntity;
import com.lovo.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "eventService")
public class EventServiceImpl implements IEventService {

    @Autowired
    private IEventDao eventDao;
    @Override
    public List<EventEntity> findEventEntitiesByCondition(String eventId, String eventType, String eventTime, int pageNo, int pageSize) {
        pageNo=pageSize*(pageNo-1);
        return  eventDao.findEventEntitiesByCondition(eventId,eventType,eventTime,pageNo,pageSize);
    }

    @Override
    public int findAllEventEntitiesNumberByCondition(String eventId, String eventType, String eventTime) {
        return eventDao.findAllEventEntitiesNumberByCondition(eventId,eventType,eventTime);
    }

    @Override
    public EventEntity findEventByEventId(String eventId) {
        return eventDao.findEventByEventId(eventId);
    }


}
