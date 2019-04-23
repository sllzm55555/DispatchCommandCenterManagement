package com.lovo.dao;

import com.lovo.entity.EventLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 事件日志
 * author 刘金林
 */
public interface IEventLogDao extends CrudRepository<EventLogEntity, String> {

    /**
     * 分页查找满足情况的所有日志
     * @param pageable 分页情况
     * @param eventId 条件中的事件编号
     * @param operateType 操作类型
     * @param operator 操作人
     * @param operateTime 操作时间
     * @return
     */
    @Query("select e from EventLogEntity e where operator=?4")
    public List<EventLogEntity> getEventLogListByPage(Pageable pageable, String eventId, String operateType, String operator, String operateTime);

    /**
     * 使用hibernate中的方法实现分页查询
     * @param operator
     * @param pageable
     * @return
     */


    @Query(value = "SELECT * FROM t_event_log as e where if(?1 is not null,e.operator like ?1,1=1) \n#pageable\n",
            countQuery = "SELECT count(*) FROM t_event_log as e where if(?1 is not null,e.operator like ?1,1=1)",
            nativeQuery = true)
    public List<EventLogEntity> getAllEventLogByCondition(String operator, Pageable pageable);

}
