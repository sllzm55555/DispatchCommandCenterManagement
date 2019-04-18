package com.lovo.service.impl;

import com.lovo.dao.DealWithEdDao;
import com.lovo.entity.EventEntity;
import com.lovo.service.IDealWithEdService;
import com.lovo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        PageRequest page =new PageRequest(pageNum,pageSize);
        List<EventEntity> list = dealWithEdDao.showDealWithEdEventList(eventPeriod,page);
        return list;
    }

    @Override
    public int getPageAll() {
        List<EventEntity> list = (List<EventEntity>) dealWithEdDao.findAll();
        int pageSize = StringUtil.PAGESIZE;
        int allUserSize = list.size();
        if (allUserSize % pageSize == 0){
            allUserSize = allUserSize / pageSize;
        }else {
            allUserSize = allUserSize / pageSize + 1;
        }
        return allUserSize;
    }
}
