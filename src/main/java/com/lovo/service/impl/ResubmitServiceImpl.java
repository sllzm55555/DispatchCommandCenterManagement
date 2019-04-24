package com.lovo.service.impl;

import com.lovo.dao.IResubmitDao;
import com.lovo.dto.ResubmitDto;
import com.lovo.entity.ResourceEntity;
import com.lovo.entity.ResubmitEntity;
import com.lovo.service.IResubmitService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 续报的业务实现层
 * @author 阿枫
 * @date 2019-04-19
 */
@Service(value = "resubmitService")
public class ResubmitServiceImpl implements IResubmitService {

    @Autowired
    IResubmitDao resubmitDao;
    @Override
    public List<ResubmitDto> findResourceEntitiesByEventEntityId(String eventEntityId, int pageNo, int pageSize,int eventPeriod) {
        pageNo=pageSize*(pageNo-1);
        List<Object[]> list = resubmitDao.findResourceEntitiesByEventEntityId(eventEntityId, pageNo, pageSize,eventPeriod);
        List<ResubmitDto> rList = null;
        if (null!=list){
            rList = new ArrayList<ResubmitDto>();
        for (Object[] objects : list) {
            ResubmitDto r = new ResubmitDto();
            r.setEventLevel(objects[0].toString());
            r.setHurtPopulation(objects[1].toString());
            r.setEventType(objects[2].toString());
            r.setReportDesc(objects[3].toString());
            rList.add(r);
        }
        }
        return rList;
    }

    @Override
    public int getAllResourNumber(String eventEntityId,int eventPeriod) {
        List<Object[]> all = resubmitDao.getAllResourNumber(eventEntityId,eventPeriod);
        List<ResubmitDto> rList = new ArrayList<ResubmitDto>();
        int totalpage=0;
        if (null!=all){
        for (Object[] objects : all) {
            ResubmitDto r = new ResubmitDto();
            r.setEventLevel(objects[0].toString());
            r.setHurtPopulation(objects[1].toString());
            r.setEventType(objects[2].toString());
            r.setReportDesc(objects[3].toString());
            rList.add(r);
        }
        totalpage=rList.size();
        int pageSize= 1;
        totalpage=totalpage%pageSize==0?totalpage/pageSize:totalpage/pageSize+1;
        }

        return totalpage;
    }

    @Override
    @Transactional
    public void changeResubmitPeriod(String eventId) {
        resubmitDao.changeResubmitPeriod(eventId);
    }


    @Override
    public List<ResubmitDto> findAllResubmitListByIdAndPeriod(String eventId, int period) {
        List<Object[]> all = resubmitDao.getAllResourNumber(eventId, period);
        List<ResubmitDto> rList =null;
        if (null != all) {
            rList = new ArrayList<ResubmitDto>();
            for (Object[] objects : all) {
                ResubmitDto r = new ResubmitDto();
                r.setEventLevel(objects[0].toString());
                r.setHurtPopulation(objects[1].toString());
                r.setEventType(objects[2].toString());
                r.setReportDesc(objects[3].toString());
                rList.add(r);
            }
        }
            return rList;
    }

    @Override
    public ResubmitDto getHotNewsResubmit(String eventId, int reperiod) {
        List<ResubmitDto> all = findAllResubmitListByIdAndPeriod(eventId, reperiod);
        ResubmitDto re=null;
       if (all!=null){
           re = all.get(0);
       }
        return re;
    }
}
