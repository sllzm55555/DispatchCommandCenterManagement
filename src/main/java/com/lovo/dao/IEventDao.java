package com.lovo.dao;

import com.lovo.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEventDao extends CrudRepository<EventEntity,String> {

    /**
     * 带条件查询，实现分页
     * @param eventId 需要条件 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @param pageNo 当前页数
     * @param pageSize 每页显示多少条
     * @return 返回符合条件的未处理事件集合
     */

    @Query(value = "select * from t_event as e " +
            "  where if(?1!='',e.event_id = ?1,1=1)" +
            "  and if(?2!='',e.event_type like %?2% ,1=1) " +
            "  and if(?3!='',e.event_time>=?3,1=1) " +
            " limit ?4,?5 ",nativeQuery = true)
    public List<EventEntity> findEventEntitiesByCondition(String eventId,String eventType,String eventTime, int pageNo,int pageSize);

    /**
     * 查询符合当前条件的总记录条数
     * @param eventId 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @return  返回符合条件的总记录条数
     */
    @Query(value = "select count(*) from t_event as e  where if(?1!='',e.event_id=?1,1=1) " +
            " and if(?2!='',e.event_type like ?2,1=1)" +
            " and if(?3!='',e.event_time>=?3)" ,nativeQuery = true)
    public int  findAllEventEntitiesNumberByCondition(String eventId,String eventType,String eventTime);

    /**
     *  通过事件Id得到事件对象
     * @param eventId 事件Id
     * @return 返回封装好对象的实体
     */
    public EventEntity findEventByEventId(String eventId);
}
