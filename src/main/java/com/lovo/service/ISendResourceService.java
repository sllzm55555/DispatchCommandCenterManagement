package com.lovo.service;

import com.lovo.dto.SendResourceDto;
import com.lovo.dto.SendResourcesDto;
import com.lovo.dto.out.EventSendDto;
import com.lovo.entity.SendResourceEntity;

import java.util.List;

/**
 * 单位派遣记录
 *
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
     *
     * @param eventId 事件id
     * @return
     */
    List<SendResourcesDto> getSendResourcesListByEventId(String eventId);

    /**
     * 发送派遣信息0失败 1成功
     *
     * @param eventId      事件id
     * @param resourceName 所有单位url
     * @param renshu       所有单位选择人数
     * @param cheliang     所有单位选择车辆
     */
    int callSendResource(String eventId, String[] resourceName, String[] renshu, String[] cheliang);

    /**
     * 保存list
     */
    void saveSendResource(List<SendResourceEntity> sendResourceEntityList);

    /**
     * 当资源返回派遣信息时通过事件ID和派遣编号修改该事件的负责人姓名电话
     * @param chargeName
     * @param chargeTel
     * @param eventId
     * @return
     */
    public int updateByEventEntity_EventIdAndRequestId(String chargeName, String chargeTel, String eventId/*, String requestId*/);

    /**
     * 修改进度
     * @param n
     * @param eventSendDto
     * @return
     */
    public int updateProgress(int n, EventSendDto eventSendDto);

    /**
     *
     * 通过事件id和派遣编号查找对应的派遣信息
     */
    public SendResourceEntity findByEventId(String eventId/*, String requestId*/);

    public SendResourceEntity findByEventIdAndRequestId(String eventId, String requestId);

    /**
     * 通过事件id找到当前派遣次数
     * @param eventId
     * @return
     */
    public SendResourceEntity findMaxRequestTime(String eventId);
}
