package com.lovo.dao;

import com.lovo.entity.EventLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 事件日志
 * author 刘金林
 */
public interface IEventLogDao extends CrudRepository<EventLogEntity, String> {

    /**
     * 分页查找满足情况的所有日志
     * @param  pageNum 页数
     * @param eventId 条件中的事件编号
     * @param operateType 操作类型
     * @param operator 操作人
     * @param operateTime 操作时间
     * @return
     */
    @Query(value = "SELECT * FROM t_event_log where " +
            " if(?1 is not null,event_id like concat('%',?1,'%'),1=1) " +
            " and if(?2 is not null ,operator like concat('%',?2,'%'),1=1)" +
            " and if(?3 is not null ,operate_time like CONCAT('%',?3,'%'),1=1)" +
            " and if(?4 is not null ,operate_type like CONCAT('%',?4,'%'),1=1)"+
            " limit ?5,?6",
            nativeQuery = true)
    public List<EventLogEntity> showEventLogListByPage(String eventId,
                                                       String operator,
                                                       String operateTime,
                                                       Integer operateType,
                                                       Integer pageNum,
                                                       Integer pageSize);




    /**
     * 得到满足情况的所有日志数
     * @param eventId
     * @param operateType
     * @param operator
     * @param operateTime
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_event_log where if(?1 is not null,event_id like concat('%',?1,'%'),1=1) " +
            " and if(?2 is not null ,operate_type like concat('%',?2,'%'),1=1)" +
            " and if(?3 is not null ,operator like CONCAT('%',?3,'%'),1=1)" +
            " and if(?4 is not null ,operate_type like CONCAT('%',?4,'%'),1=1)",
            nativeQuery = true)
    public Integer getCount(String eventId, Integer operateType, String operator, String operateTime);
}
