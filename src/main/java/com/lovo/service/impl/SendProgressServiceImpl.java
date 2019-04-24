package com.lovo.service.impl;

import com.lovo.dao.ISendProgressDao;
import com.lovo.dto.ProgressDto;
import com.lovo.entity.SendProgressEntity;
import com.lovo.service.ISendProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "sendProgressService")
public class SendProgressServiceImpl implements ISendProgressService {

    @Autowired
    private ISendProgressDao sendProgressDao;

    @Override
    public List<ProgressDto> findAllProgressDto(String eventId) {
        return sendProgressDao.findAllProgressByEventId(eventId);
    }

    @Override
    public void saveSendResourceEntityList(List<SendProgressEntity> sendProgressEntityList) {
        sendProgressDao.save(sendProgressEntityList);
    }

    @Override
    @Transactional
    public void updateReturnTimeByEventIdAndSendProgressId(String returnTime, String resourceId, String eventId) {
        sendProgressDao.updateReturnTimeByEventIdAndSendProgressId(returnTime, resourceId, eventId);
    }
}
