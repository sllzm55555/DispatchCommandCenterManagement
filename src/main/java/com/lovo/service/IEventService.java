package com.lovo.service;

import com.lovo.entity.EventEntity;

import java.util.List;

/**
 * 事件的Service
 * @author 阿枫
 * @date  2019-04-19
 */
public interface IEventService {
    /**
     * 带条件查询，实现分页
     * @param eventId 需要条件 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @param pageNo 当前页数
     * @param pageSize 每页显示多少条
     * @param eventPeriod 当前的事件阶段
     * @return 返回符合条件的未处理事件集合
     */
    public List<EventEntity> findEventEntitiesByCondition(String eventId,String eventType,String eventTime, int pageNo,int pageSize,int eventPeriod);

    /**
     * 查询符合当前条件的记录总共有多少页
     * @param eventId 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @param eventPeriod 当前的事件阶段
     * @return  返回符合条件的总数据页数
     */
    public int  getTotalNumber(String eventId,String eventType,String eventTime,int eventPeriod);

    /**
     * 通过事件Id得到事件的实体对象
     * @param eventId 事件ID
     * @return 返回实例化的对象
     */
    public EventEntity findEventByEventId(String eventId);
}
