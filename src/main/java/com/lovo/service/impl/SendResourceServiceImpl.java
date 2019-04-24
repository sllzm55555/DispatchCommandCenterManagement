package com.lovo.service.impl;

import com.lovo.dao.ISendResourceDao;
import com.lovo.dto.SendResourceDto;
import com.lovo.dto.SendResourcesDto;
import com.lovo.service.ISendResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "sendResourceService")
public class SendResourceServiceImpl implements ISendResourceService {

    @Autowired
    private ISendResourceDao sendResourceDao;

    @Override
    public List<SendResourceDto> findAllSendResourceByEventId(String eventId) {
        return sendResourceDao.findAllSendResourceByEventId(eventId);
    }

    @Override
    public List<SendResourcesDto> getSendResourcesListByEventId(String eventId) {
        List<SendResourcesDto> list = new ArrayList<>();

        List<Object[]> listByEventId = sendResourceDao.getSendResourcesListByEventId(eventId);
        SendResourcesDto sendResourcesDto = new SendResourcesDto();

        int n = 0;
        for (Object[] objects : listByEventId) {
            int times = (int) objects[0];
            if (n == times) {
                sendResourcesDto.setTime(times);
                for (int i = 0; i < objects.length; i++) {
                    if ("110".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeNature((String) objects[1]);
                        sendResourcesDto.setNaturePolulation((Integer) objects[2]);
                        sendResourcesDto.setNatureResource((Integer) objects[3]);
                        break;
                    }
                    if ("120".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                        sendResourcesDto.setHospitalPolulation((Integer) objects[2]);
                        sendResourcesDto.setHospitalResource((Integer) objects[3]);
                        break;
                    }
                    if ("119".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeFire((String) objects[1]);
                        sendResourcesDto.setFirePolulation((Integer) objects[2]);
                        sendResourcesDto.setFireResource((Integer) objects[3]);
                        break;
                    }
                }
            } else {
                list.add(sendResourcesDto);
                sendResourcesDto = new SendResourcesDto();
                for (int i = 0; i < objects.length; i++) {
                    if ("110".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeNature((String) objects[1]);
                        sendResourcesDto.setNaturePolulation((Integer) objects[2]);
                        sendResourcesDto.setNatureResource((Integer) objects[3]);
                        break;
                    }
                    if ("120".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                        sendResourcesDto.setHospitalPolulation((Integer) objects[2]);
                        sendResourcesDto.setHospitalResource((Integer) objects[3]);
                        break;
                    }
                    if ("119".equals(objects[1])) {
                        sendResourcesDto.setDeptTypeFire((String) objects[1]);
                        sendResourcesDto.setFirePolulation((Integer) objects[2]);
                        sendResourcesDto.setFireResource((Integer) objects[3]);
                        break;
                    }
                }
            }
            n = times;
        }

        list.add(sendResourcesDto);
        SendResourcesDto resourcesDto = new SendResourcesDto();
        int ren110 = 0;
        int che110 = 0;
        int ren120 = 0;
        int che120 = 0;
        int ren119 = 0;
        int che119 = 0;

        for (int i = 0; i < list.size(); i++) {
            ren110 += list.get(i).getNaturePolulation();
            che110 += list.get(i).getNatureResource();
            ren120 += list.get(i).getHospitalPolulation();
            che120 += list.get(i).getHospitalResource();
            ren119 += list.get(i).getFirePolulation();
            che119 += list.get(i).getFireResource();
        }
        resourcesDto.setTime(-1);
        resourcesDto.setDeptTypeNature("110");
        resourcesDto.setNaturePolulation(ren110);
        resourcesDto.setNatureResource(che110);
        resourcesDto.setDeptTypeHospital("120");
        resourcesDto.setHospitalPolulation(ren120);
        resourcesDto.setHospitalResource(che120);
        resourcesDto.setDeptTypeFire("119");
        resourcesDto.setFirePolulation(ren119);
        resourcesDto.setFireResource(che119);

        list.add(resourcesDto);

        return list;
    }
}
