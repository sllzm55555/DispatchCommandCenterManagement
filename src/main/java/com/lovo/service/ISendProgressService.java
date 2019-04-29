package com.lovo.service;

import com.lovo.dto.ProgressDto;
import com.lovo.entity.SendProgressEntity;

import java.util.List;

/***
 * 资源派遣进度service
 * @date
 * @author lin
 */
public interface ISendProgressService {

    /**
     * 通过事件Id查找该事件派出的所有资源个体Dto
     * @param eventId 事件ID
     * @return
     */
    public List<ProgressDto> findAllProgressDto(String eventId);

    public void saveSendProgressEntity(SendProgressEntity sendProgressEntity);

    /***
     * 保存资源进度信息
     * @param sendProgressEntityList
     */
    public void saveSendResourceEntityList(List<SendProgressEntity> sendProgressEntityList);

    /***
     * 通过事件ID和资源个体ID修改资源归队时间
     * @param returnTime 资源归队时间
     * @param eventId 事件ID
     * @param resourceId 资源个体ID
     */
    public void updateReturnTimeByEventIdAndSendProgressId(String returnTime, String resourceId, String eventId);
}
