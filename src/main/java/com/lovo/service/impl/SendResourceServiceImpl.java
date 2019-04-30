package com.lovo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dao.ISendResourceDao;
import com.lovo.dto.ResubmitDto;
import com.lovo.dto.SendResourceDto;
import com.lovo.dto.SendResourcesDto;
import com.lovo.dto.out.CarDto;
import com.lovo.dto.out.EventSendDto;
import com.lovo.dto.out.EventSinkDto;
import com.lovo.dto.out.PersonDto;
import com.lovo.entity.EventEntity;
import com.lovo.entity.ResourceEntity;
import com.lovo.entity.SendProgressEntity;
import com.lovo.entity.SendResourceEntity;
import com.lovo.service.*;
import com.lovo.util.MQUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(value = "sendResourceService")
@Transactional(rollbackFor = Exception.class)
public class SendResourceServiceImpl implements ISendResourceService {

    @Autowired
    private MQUtil mqUtil;

    @Autowired
    private ISendResourceDao sendResourceDao;

    @Autowired
    private IEventService eventService;

    @Autowired
    private ISendResourceService sendResourceService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ISendProgressService sendProgressService;

    @Autowired
    private IResubmitService resubmitService;

    @Override
    public List<SendResourceDto> findAllSendResourceByEventId(String eventId) {
        return sendResourceDao.findAllSendResourceByEventId(eventId);
    }

    @Override
    public List<SendResourcesDto> getSendResourcesListByEventId(String eventId) {
        List<SendResourcesDto> list = new ArrayList<>();

        List<Object[]> listByEventId = sendResourceDao.getSendResourcesListByEventId(eventId);
        if (listByEventId == null || listByEventId.size() <= 0 ) {
            return null;
        }

        SendResourcesDto sendResourcesDto = new SendResourcesDto();

        int n = 0;
        for (Object[] objects : listByEventId) {
            int times = (int) objects[0];
            if (n == times) {
                sendResourcesDto.setTime(times);
                for (int i = 0; i < objects.length; i++) {
                    if ("公安".equals(objects[1])) {
                        if (sendResourcesDto.getNaturePolulation() == 0 && sendResourcesDto.getNatureResource() == 0){
                            sendResourcesDto.setDeptTypeNature((String) objects[1]);
                            sendResourcesDto.setNaturePolulation((Integer) objects[2]);
                            sendResourcesDto.setNatureResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeNature((String) objects[1]);
                            sendResourcesDto.setNaturePolulation(sendResourcesDto.getNaturePolulation() + (Integer) objects[2]);
                            sendResourcesDto.setNatureResource(sendResourcesDto.getNatureResource() + (Integer) objects[3]);
                            break;
                        }
                    }
                    if ("医院".equals(objects[1])) {
                        if (sendResourcesDto.getHospitalPolulation() == 0 && sendResourcesDto.getHospitalResource() == 0){
                            sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                            sendResourcesDto.setHospitalPolulation((Integer) objects[2]);
                            sendResourcesDto.setHospitalResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                            sendResourcesDto.setHospitalPolulation(sendResourcesDto.getHospitalPolulation() + (Integer) objects[2]);
                            sendResourcesDto.setHospitalResource(sendResourcesDto.getHospitalResource() + (Integer) objects[3]);
                            break;
                        }
                    }
                    if ("消防".equals(objects[1])) {
                        if (sendResourcesDto.getFirePolulation() == 0 && sendResourcesDto.getFireResource() == 0){
                            sendResourcesDto.setDeptTypeFire((String) objects[1]);
                            sendResourcesDto.setFirePolulation((Integer) objects[2]);
                            sendResourcesDto.setFireResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeFire((String) objects[1]);
                            sendResourcesDto.setFirePolulation(sendResourcesDto.getFirePolulation() + (Integer) objects[2]);
                            sendResourcesDto.setFireResource(sendResourcesDto.getFireResource() + (Integer) objects[3]);
                            break;
                        }
                    }
                }
            } else {
                list.add(sendResourcesDto);
                sendResourcesDto = new SendResourcesDto();
                sendResourcesDto.setTime(times);
                for (int i = 0; i < objects.length; i++) {
                    if ("公安".equals(objects[1])) {
                        if (sendResourcesDto.getNaturePolulation() == 0 && sendResourcesDto.getNatureResource() == 0){
                            sendResourcesDto.setDeptTypeNature((String) objects[1]);
                            sendResourcesDto.setNaturePolulation((Integer) objects[2]);
                            sendResourcesDto.setNatureResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeNature((String) objects[1]);
                            sendResourcesDto.setNaturePolulation(sendResourcesDto.getNaturePolulation() + (Integer) objects[2]);
                            sendResourcesDto.setNatureResource(sendResourcesDto.getNatureResource() + (Integer) objects[3]);
                            break;
                        }
                    }
                    if ("医院".equals(objects[1])) {
                        if (sendResourcesDto.getHospitalPolulation() == 0 && sendResourcesDto.getHospitalResource() == 0){
                            sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                            sendResourcesDto.setHospitalPolulation((Integer) objects[2]);
                            sendResourcesDto.setHospitalResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeHospital((String) objects[1]);
                            sendResourcesDto.setHospitalPolulation(sendResourcesDto.getHospitalPolulation() + (Integer) objects[2]);
                            sendResourcesDto.setHospitalResource(sendResourcesDto.getHospitalResource() + (Integer) objects[3]);
                            break;
                        }
                    }
                    if ("消防".equals(objects[1])) {
                        if (sendResourcesDto.getFirePolulation() == 0 && sendResourcesDto.getFireResource() == 0){
                            sendResourcesDto.setDeptTypeFire((String) objects[1]);
                            sendResourcesDto.setFirePolulation((Integer) objects[2]);
                            sendResourcesDto.setFireResource((Integer) objects[3]);
                            break;
                        }else {
                            sendResourcesDto.setDeptTypeFire((String) objects[1]);
                            sendResourcesDto.setFirePolulation(sendResourcesDto.getFirePolulation() + (Integer) objects[2]);
                            sendResourcesDto.setFireResource(sendResourcesDto.getFireResource() + (Integer) objects[3]);
                            break;
                        }
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

    @Override
    public int callSendResource(String eventId, String[] resourceName, String[] renshu, String[] cheliang) {
        EventEntity eventEntity = eventService.findEventByEventId(eventId);

//        List<EventSinkDto> eventSinkDtoList = new ArrayList<>();
        List<SendResourceEntity> sendResourceEntityList = new ArrayList<>();
        //人或车数量大于0才是需要派遣的单位,拼接sendResourceEntity
        int count = 0;
        for (int i = 0; i < resourceName.length; i++) {
            int ren = 0;
            int che = 0;
            if (renshu[i] != null && renshu[i].length() > 0) {
                ren = Integer.parseInt(renshu[i]);
            }
            if (cheliang[i] != null && cheliang[i].length() > 0) {
                che = Integer.parseInt(renshu[i]);
            }
            if (ren > 0 || che > 0) {
                String rUrl = resourceName[i];
                //通过资源名字找到资源
                ResourceEntity resourceEntity = resourceService.findByUrl(rUrl);
                //保存派遣信息
                SendResourceEntity sendResourceEntity = new SendResourceEntity();
                UUID uuid = UUID.randomUUID();
                sendResourceEntity.setRequestId(uuid.toString().replace("-",""));
                sendResourceEntity.setResourceName(resourceEntity.getRname());
                sendResourceEntity.setResourceUrl(resourceEntity.getUrl());
                sendResourceEntity.setRequestPopulation(ren);
                sendResourceEntity.setRequestResource(che);
                sendResourceEntity.setRequestTimes(getRequestTime(eventId) + 1);
                sendResourceEntity.setChargeName("未指派");
                sendResourceEntity.setChargeTel("未指派");
                sendResourceEntity.setEventEntity(eventEntity);
                sendResourceEntityList.add(sendResourceEntity);
                count = count + 1;
            }
        }
        if (count < 1) {
            return 0;
        }
        //保存派遣信息
        sendResourceService.saveSendResource(sendResourceEntityList);
        //如果事件id不为2（1是未处理事件），修改事件处理状态
        if (eventEntity.getEventPeriod() != 2) {
            eventService.updateEventData(eventId,2);
        }
        //把所有续报状态改成已处理
        List<ResubmitDto> resubmitDtoList = resubmitService.findAllResubmitListByIdAndPeriod(eventId, eventEntity.getEventPeriod(), null);
        if (resubmitDtoList != null && resubmitDtoList.size() > 0) {
            resubmitService.changeResubmitPeriod(eventId);
        }
        //组装需要发送给各单位的EnevtSinkDto,并发送笑死
        for (int i = 0; i < sendResourceEntityList.size(); i++) {
            EventSinkDto eventSinkDto = new EventSinkDto();
            eventSinkDto.setId(eventId);
            eventSinkDto.setEventName(eventEntity.getEventName());
            eventSinkDto.setEventType(eventEntity.getEventType());
            eventSinkDto.setEventArea(eventEntity.getEventArea());
            eventSinkDto.setEventTime(Timestamp.valueOf(eventEntity.getEventTime()));
            eventSinkDto.setAlarmPerson(eventEntity.getAlarmPerson());
            eventSinkDto.setAlarmTel(eventEntity.getAlarmTel());
            eventSinkDto.setAlarmAddress(eventEntity.getAlarmAddress());
            eventSinkDto.setRequestId(sendResourceEntityList.get(i).getRequestId());
            eventSinkDto.setPersonNum(sendResourceEntityList.get(i).getRequestPopulation());
            eventSinkDto.setCarNum(sendResourceEntityList.get(i).getRequestResource());
            //循环派遣信息，向每个单位发送派遣信息
            mqUtil.sendEventSinkDto(sendResourceEntityList.get(i).getResourceUrl(),eventSinkDto);

        }

        return 1;
    }

    @Override
    public void saveSendResource(List<SendResourceEntity> sendResourceEntityList) {
        sendResourceDao.save(sendResourceEntityList);
    }

    @Override
    public int updateByEventEntity_EventIdAndRequestId(String chargeName, String chargeTel, String eventId, String requestId) {
        int n = sendResourceDao.updateByEventEntity_EventIdAndRequestId(chargeName, chargeTel, eventId, requestId);
        return n;
    }

    /**
     * 得到资源进度，修改或保存相关信息
     * @param eventSendDto
     * @return
     */
    @Override
    public int updateProgress(int n, EventSendDto eventSendDto) {
        //1修改成功，则是初次派遣，新建单个资源派遣进度   0修改失败，则是归队信息
        //所以派遣dto必须写负责人，并且负责人在list里面
        //归队也必须写负责人，负责人可不用写在list里面
        EventEntity eventEntity = eventService.findEventByEventId(eventSendDto.getId());
        SendResourceEntity sendResourceEntity = sendResourceService.findByEventIdAndRequestId(eventEntity.getEventId(), eventSendDto.getRequestId());
        List<PersonDto> personDtos = eventSendDto.getPersonDtos();
        List<CarDto> carDtos = eventSendDto.getCarDtos();
        if (n == 1) {
            List<SendProgressEntity> sendProgressEntityList = new ArrayList<>();
            if (personDtos != null) {
                for (int i = 0 ; i < personDtos.size() ; i++) {
                    SendProgressEntity sendProgressEntity = new SendProgressEntity();
                    sendProgressEntity.setDeptName(sendResourceEntity.getResourceName());
                    sendProgressEntity.setResourceName(personDtos.get(i).getPersonName());
                    sendProgressEntity.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(personDtos.get(i).getStartTime()));
                    sendProgressEntity.setReturnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(personDtos.get(i).getReturnTime()));
                    sendProgressEntity.setResourceId(personDtos.get(i).getId());
                    sendProgressEntity.setSendResourceEntity(sendResourceEntity);
                    sendProgressEntity.setEventEntity(eventEntity);
                    sendProgressEntityList.add(sendProgressEntity);
                }
            }
            if (carDtos != null) {
                for (int i = 0 ; i <carDtos.size() ; i++) {
                    SendProgressEntity sendProgressEntity = new SendProgressEntity();
                    sendProgressEntity.setDeptName(sendResourceEntity.getResourceName());
                    sendProgressEntity.setResourceName(carDtos.get(i).getCarNum());
                    sendProgressEntity.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(carDtos.get(i).getStartTime()));
                    sendProgressEntity.setReturnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(carDtos.get(i).getReturnTime()));
                    sendProgressEntity.setResourceId(carDtos.get(i).getId());
                    sendProgressEntity.setSendResourceEntity(sendResourceEntity);
                    sendProgressEntity.setEventEntity(eventEntity);
                    sendProgressEntityList.add(sendProgressEntity);
                }
            }

            if (sendProgressEntityList != null && sendProgressEntityList.size() >0) {
                sendProgressService.saveSendResourceEntityList(sendProgressEntityList);
            }

            return 1;
        } else {
            if (personDtos != null) {
                for (int i = 0 ; i < personDtos.size() ; i++) {
                    sendProgressService.updateReturnTimeByEventIdAndSendProgressId(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(personDtos.get(i).getReturnTime()),personDtos.get(i).getId(),eventEntity.getEventId());
                }
            }
            if (carDtos != null) {
                for (int i = 0 ; i < carDtos.size() ; i++) {
                    sendProgressService.updateReturnTimeByEventIdAndSendProgressId(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(carDtos.get(i).getReturnTime()),carDtos.get(i).getId(),eventEntity.getEventId());
                }
            }

            return 0;
        }
    }

    @Override
    public SendResourceEntity findByEventIdAndRequestId(String eventId, String requestId) {
        return sendResourceDao.findByEventEntity_EventIdAndRequestId(eventId, requestId);
    }

    @Override
    public SendResourceEntity findMaxRequestTime(String eventId) {
        return sendResourceDao.findMaxRequestTime(eventId);
    }

    /**
     * 得到派遣当前已派遣次数
     * @param eventId
     * @return
     */
    private int getRequestTime(String eventId){
        SendResourceEntity sendResourceEntity = sendResourceDao.findMaxRequestTime(eventId);
        if (sendResourceEntity != null) {
            return sendResourceEntity.getRequestTimes();
        }
        return -1;
    }
}
