package com.lovo.dao;

import com.lovo.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealWithEdDao extends CrudRepository<EventEntity,String> {

    /**
     * 模糊查询
     * @param eventPeriod 事件阶段
     * @param index 起始下标
     * @param pageSize 每页数量
     * @return
     */
    @Query(value = "select * from t_event e where e.event_period = ?1 limit ?2,?3 "
            ,nativeQuery = true)
    List<EventEntity> showDealWithEdEventList(int eventPeriod,int index,int pageSize);

    /**
     * 根据状态查询所有的完成事件
     * @param eventPeriod 事件状态
     * @return
     */
    @Query(value = "select * from t_event e where e.event_period = ?1",nativeQuery = true)
    List<EventEntity> findAllByEventPeriod(int eventPeriod);


    /**
     * 模糊查询
     * @param eventId  事件编号
     * @param eventName 事件名称
     * @param eventType 事件类型
     * @param endTime 结束时间
     * @param index 起始下标
     * @param pageSize 每页的条数
     * @return
     */
    @Query(value = "select * from t_event e where if(:eventId is not null ,e.event_id like CONCAT('%',:eventId,'%'),1=1)" +
            " and if(:eventName is not null ,e.event_name like CONCAT('%',:eventName,'%'),1=1)" +
            " and if(:eventType is not null ,e.event_type like CONCAT('%',:eventType,'%'),1=1)" +
            " and if(:endTime is not null ,e.end_time like CONCAT('%',:endTime,'%'),1=1)" +
            " and e.event_period = :eventPeriod limit :index,:pageSize"
            ,nativeQuery = true)
    List<EventEntity> findAll(@Param("eventId") String eventId, @Param("eventName") String eventName,
                              @Param("eventType") String eventType,@Param("endTime") String endTime,
                              @Param("eventPeriod") int eventPeriod,@Param("index")int index,
                              @Param("pageSize")int pageSize);

    /**
     * 根据条件查询事件的总条数
     * @param eventId 事件编号
     * @param eventName 事件名称
     * @param eventType 事件类型
     * @param endTime 结束时间
     * @param eventPeriod 事件阶段
     * @return
     */
    @Query(value = "select * from t_event e where if(:eventId is not null ,e.event_id like CONCAT('%',:eventId,'%'),1=1)" +
            " and if(:eventName is not null ,e.event_name like CONCAT('%',:eventName,'%'),1=1)" +
            " and if(:eventType is not null ,e.event_type like CONCAT('%',:eventType,'%'),1=1)" +
            " and if(:endTime is not null ,e.end_time like CONCAT('%',:endTime,'%'),1=1)" +
            " and e.event_period = :eventPeriod",nativeQuery = true)
    List<EventEntity> findAll(@Param("eventId") String eventId, @Param("eventName") String eventName,
                              @Param("eventType") String eventType,@Param("endTime") String endTime,
                              @Param("eventPeriod") int eventPeriod);
}
