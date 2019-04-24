package com.lovo.service;

import com.lovo.dto.SendResourceDto;

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
}
