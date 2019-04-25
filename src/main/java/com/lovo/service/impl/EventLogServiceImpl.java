package com.lovo.service.impl;

import com.lovo.dao.IEventLogDao;
import com.lovo.entity.EventLogEntity;
import com.lovo.service.IEventLogService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "eventLogService")
public class EventLogServiceImpl implements IEventLogService {

    @Autowired
    private IEventLogDao eventLogDao;

    /**
     *
     * @param currpage 当前页数
     * @param pageSize
     * @param eventId
     * @param operateType
     * @param operator
     * @param operateTime
     * @return
     */
    @Override
    public List<EventLogEntity> showEventLogByPage(Integer currpage,Integer pageSize,String eventId, Integer operateType, String operator, String operateTime) {
        //判断是否为空

        int index = (currpage - 1)* pageSize;
        List<EventLogEntity> eventLogList = eventLogDao
                                            .showEventLogListByPage(eventId,
                                                    operator,
                                                    operateTime,
                                                    operateType,
                                                    index,pageSize);

        System.out.println(eventLogList.size());
        return eventLogList;

    }

    @Override
    public EventLogEntity saveEventLog(String eLogId,String operator,String operateTime,Integer operateType,String eventId) {

        EventLogEntity eventLogEntity = new EventLogEntity();
        eventLogEntity.seteLogId(eLogId);
        eventLogEntity.setOperator(operator);
        eventLogEntity.setOperateTime(operateTime);
        eventLogEntity.setOperateType(operateType);
        eventLogEntity.setEventId(eventId);

        return eventLogDao.save(eventLogEntity);
    }

    @Override
    public Integer getCount(String eventId, Integer operateType, String operator, String operateTime,Integer pageSize) {
        if (null==eventId||"".equals(eventId)){
            eventId=null;
        }
        /*if (operateType==0||"".equals(operateType)){
            operateType=null;
        }*/
        if (null==operator||"".equals(operator)){
            operator=null;
        }
        if (null==operateTime||"".equals(operateTime)){
            operateTime=null;
        }
        int count = eventLogDao.getCount(eventId,operateType,
                                            operator,operateTime);
        int size = pageSize;
        int totalPage = (count + size - 1)/size;
        return totalPage;
    }


}
