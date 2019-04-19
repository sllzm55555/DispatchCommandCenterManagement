package com.lovo.service;

import com.lovo.entity.EventEntity;

import java.util.List;

public interface IEventService {
    /**
     * 带条件查询，实现分页
     * @param eventId 需要条件 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @param pageNo 当前页数
     * @param pageSize 每页显示多少条
     * @return 返回符合条件的未处理事件集合
     */
    public List<EventEntity> findEventEntitiesByCondition(String eventId,String eventType,String eventTime, int pageNo,int pageSize);

    /**
     * 查询符合当前条件的总记录条数
     * @param eventId 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @return  返回符合条件的总记录条数
     */
    public int  findAllEventEntitiesNumberByCondition(String eventId,String eventType,String eventTime);

    public EventEntity findEventByEventId(String eventId);
}
