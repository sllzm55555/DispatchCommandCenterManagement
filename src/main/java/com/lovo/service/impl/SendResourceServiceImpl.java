package com.lovo.service.impl;

import com.lovo.dao.ISendResourceDao;
import com.lovo.dto.SendResourceDto;
import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "sendResourceService")
public class SendResourceServiceImpl implements ISendResourceService {

    @Autowired
    private ISendResourceDao sendResourceDao;

    @Override
    public List<SendResourceDto> findAllSendResourceByEventId(String eventId) {
        return sendResourceDao.findAllSendResourceByEventId(eventId);
    }
}
