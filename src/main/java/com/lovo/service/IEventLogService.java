package com.lovo.service;

import com.lovo.entity.EventLogEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 事件日志接口
 */
public interface IEventLogService {

    /**
     * 分页查找满足情况的所有日志
     * @param pageable 分页情况
     * @param eventId 条件中的事件编号
     * @param operateType 操作类型
     * @param operator 操作人
     * @param operateTime 操作时间
     * @return
     */
    public List<EventLogEntity> showEventLogListPage(String eventId, int operateType, String operator, String operateTime,Pageable pageable);

    /**
     * 首先需要的得到事件操作相关信息，
     * 保存事件日志信息
     * @param eLogId 事件日志id
     * @param operator 操作人
     * @param operateTime 操作时间
     * @param operateType 操作类型
     * @param eventId 时间id
     * @return
     */
    public EventLogEntity saveEventLog(String eLogId,String operator,String operateTime,int operateType,String eventId);

    /**
     * 测试分页查询
     * @param operator
     * @param pageable
     * @return
     */
    public List<EventLogEntity> getAllEventLogByPage(String operator,Pageable pageable);

}
