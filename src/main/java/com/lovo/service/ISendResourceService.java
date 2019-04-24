package com.lovo.service;

import com.lovo.dto.SendResourceDto;
import com.lovo.dto.SendResourcesDto;

import java.util.List;

/**
 * 单位派遣记录
 * @author lin
 * @date
 */
public interface ISendResourceService {

    /***
     * 通过事件ID查找该事件所派出的所有sendResource信息
     * @param eventId   事件ID
     * @return
     */
    public List<SendResourceDto> findAllSendResourceByEventId(String eventId);

    /**
     * 通过事件id得到一个事件派遣DTO集合
     * @param eventId 事件id
     * @return
     */
    List<SendResourcesDto> getSendResourcesListByEventId(String eventId);
}
