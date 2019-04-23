package com.lovo.service.impl;

import com.lovo.dao.IEventLogDao;
import com.lovo.entity.EventLogEntity;
import com.lovo.service.IEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "eventLogService")
public class EventLogServiceImpl implements IEventLogService {

    @Autowired
    private IEventLogDao eventLogDao;

    @Override
    public List<EventLogEntity> showEventLogListPage(String eventId, int operateType, String operator, String operateTime,Pageable pageable) {
        //在controller中Pageable pageable=new PageRequest(0, 2);表示每页显示几条数据，从哪里开始

        if(null!=eventId&&!"".equals(eventId)){

        }

        //List<EventLogEntity> eventLogEntityList = eventLogDao.getEventLogListByPage(pageable, eventId, ""+operateType, "", "");
        return null;
    }

    @Override
    public EventLogEntity saveEventLog(String eLogId,String operator,String operateTime,int operateType,String eventId) {

        EventLogEntity eventLogEntity = new EventLogEntity();
        eventLogEntity.seteLogId(eLogId);
        eventLogEntity.setOperator(operator);
        eventLogEntity.setOperateTime(operateTime);
        eventLogEntity.setOperateType(operateType);
        eventLogEntity.setEventId(eventId);

        return eventLogDao.save(eventLogEntity);
    }

    @Override
    public List<EventLogEntity> getAllEventLogByPage(String operator, Pageable pageable) {
        List<EventLogEntity> eventLogEntityList = eventLogDao.getAllEventLogByCondition(operator, pageable);
        return eventLogEntityList;
    }
}
