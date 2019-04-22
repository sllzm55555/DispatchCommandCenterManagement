package com.lovo.service.impl;

import com.lovo.dao.DealWithEdDao;
import com.lovo.entity.EventEntity;
import com.lovo.service.IDealWithEdService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "dealWithEdService")
public class DealWithEdServiceImpl implements IDealWithEdService {

    @Autowired
    private DealWithEdDao dealWithEdDao;

    @Override
    public List<EventEntity> showDealWithEdEventList(int pageNum) {
        int eventPeriod = StringUtil.EVENTPERIODDEALWITHED;
        int pageSize = StringUtil.PAGESIZE;
        pageNum = pageSize * (pageNum - 1);
        List<EventEntity> list = dealWithEdDao.showDealWithEdEventList(eventPeriod,pageNum,pageSize);
        return list;
    }

    @Override
    public int getPageAll(int pageNum) {
        int pageSize = StringUtil.PAGESIZE;
        int eventPeriod = StringUtil.EVENTPERIODDEALWITHED;
        List<EventEntity> list = dealWithEdDao.findAllByEventPeriod(eventPeriod);
        int allUserSize = list.size();
        if (allUserSize % pageSize == 0){
            allUserSize = allUserSize / pageSize;
        }else {
            allUserSize = allUserSize / pageSize + 1;
        }
        return allUserSize;
    }


    @Override
    public List<EventEntity> findAll(int pageNum, EventEntity event) {
        int pageSize = StringUtil.PAGESIZE;
        pageNum = pageSize * (pageNum - 1);
        return dealWithEdDao.findAll(event.getEventId()
                ,event.getEventName(),event.getEventType()
                ,event.getEndTime(),StringUtil.EVENTPERIODDEALWITHED,pageNum,pageSize);
    }

    @Override
    public int pageAll(int pageNum,EventEntity event) {
        int pageSize = StringUtil.PAGESIZE;
        int eventPeriod = StringUtil.EVENTPERIODDEALWITHED;
        List<EventEntity> list = dealWithEdDao.findAll(event.getEventId()
                ,event.getEventName(),event.getEventType()
                ,event.getEndTime(),eventPeriod);

        double allUserSize = list.size();

        allUserSize = Math.ceil(allUserSize / pageSize);

        return (int) allUserSize;
    }

    @Override
    public EventEntity findDealWithEventById(String eventId) {

        return dealWithEdDao.findDealWithEventById(eventId);
    }
}
