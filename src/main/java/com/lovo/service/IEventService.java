package com.lovo.service;

import com.lovo.entity.EventEntity;

import java.sql.Timestamp;
import java.util.Date;
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


    /**
     * 事件处理完成的时候，把事件完成的时间设置回去
     * @param date 当前时间
     * @param eventId 事件的id
     * @return 成功返回1,失败返回0
     */
    public int changeEventEndTime(Date date,String eventId);

    /**
     * 新建事务
     * @param e 事务对象
     * @return 成功返回对象，失败返回null
     */
    public EventEntity saveEvent(EventEntity e);


    /**
     *  改变事件受伤人数，灾害等级，事件进度
     * @param eventId 事件Id
     * @param eventPeriod 事件的进行状态
     * @param reperiod  续报的处理阶段
     * @return 修改成功返回1，失败返回0，没有进行修改返回-1
     *
     */
    public int updateEventData(String eventId,int eventPeriod);

    public int endEvent(String eventId, String timestamp);

}
