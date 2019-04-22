package com.lovo.dao;

import com.lovo.entity.EventEntity;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * 事件的Dao层
 * @author 阿枫
 * @date 2019-04-19
 */
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
            "  where if(?1 is not null,e.event_id like ?1,1=1)" +
            "  and if(?2 is not null,e.event_type like concat('%',?2,'%') ,1=1) " +
            "  and if(?3 is not null,e.event_time >= ?3,1=1) " +
            "  limit ?4,?5 ",nativeQuery = true)
    public List<EventEntity> findEventEntitiesByCondition(String eventId,
                                                          String eventType,
                                                          String eventTime,
                                                          int pageNo,
                                                          int pageSize);

    /**
     * 查询符合当前条件的总记录条数
     * @param eventId 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @return  返回符合条件的总记录条数
     */
    @Query(value = "select * from t_event as e where" +
            " if(?1 is not null,e.event_id like ?1,1=1) " +
            " and if(?2 is not null,e.event_type like concat('%',?2,'%'),1=1)" +
            " and if(?3 is not null,e.event_time >=?3,1=1)" ,nativeQuery = true)
    public List<EventEntity>  findAllEventEntitiesNumberByCondition( String eventId,
                                                       String eventType,
                                                       String eventTime);

    /**
     *  通过事件Id得到事件对象
     * @param eventId 事件Id
     * @return 返回封装好对象的实体
     */
    @Query("select e from EventEntity e where e.eventId =?1")
    public EventEntity findEventByEventId(String eventId);
}
