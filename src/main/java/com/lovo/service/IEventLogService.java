package com.lovo.service;

import com.lovo.entity.EventLogEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 事件日志接口
 *
 * 刘金林
 */
public interface IEventLogService {

    /**
     * 保存事件日志信息
     * 首先需要的得到事件操作相关信息，
     * @param eLogId 事件日志id
     * @param operator 操作人
     * @param operateTime 操作时间
     * @param operateType 操作类型
     * @param eventId 时间id
     * @return
     */
    public EventLogEntity saveEventLog(String eLogId, String operator, String operateTime, Integer operateType, String eventId);


    /**
     * 多条件分页查询，得到满足条件的日志
     * @param currpage
     * @param pageSize
     * @param eventId
     * @param operateType
     * @param operator
     * @param operateTime
     * @return
     */
    public List<EventLogEntity> showEventLogByPage(Integer currpage, Integer pageSize, String eventId, Integer operateType, String operator, String operateTime);


    /**
     * 得到满足情况的总页数
     * @param eventId
     * @param operateType
     * @param operator
     * @param operateTime
     * @param pageSize
     * @return
     */
    public Integer getCount(String eventId, Integer operateType, String operator, String operateTime, Integer pageSize);

}
