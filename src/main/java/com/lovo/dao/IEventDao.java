package com.lovo.dao;

import com.lovo.entity.EventEntity;
import feign.Param;
import org.junit.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
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
     * @param eventPeriod 当前事件阶段
     * @return 返回符合条件的未处理事件集合
     */
    @Query(value = "select * from t_event as e " +
            "  where if(?1 is not null,e.event_id like ?1,1=1)" +
            "  and if(?2 is not null,e.event_type like concat('%',?2,'%') ,1=1) " +
            "  and if(?3 is not null,e.event_time >= ?3,1=1)" +
            "  and if(?6 is not null,e.event_period=?6,1=1) " +
            "  limit ?4,?5 ",nativeQuery = true)
    public List<EventEntity> findEventEntitiesByCondition(String eventId,
                                                          String eventType,
                                                          String eventTime,
                                                          int pageNo,
                                                          int pageSize, int eventPeriod);

    /**
     * 查询符合当前条件的总记录条数
     * @param eventId 事件id
     * @param eventType  事件类型
     * @param eventTime 上报事件时间
     * @param eventPeriod 当前事件阶段
     * @return  返回符合条件的总记录条数
     */
    @Query(value = "select * from t_event as e where" +
            " if(?1 is not null,e.event_id like ?1,1=1) " +
            " and if(?2 is not null,e.event_type like concat('%',?2,'%'),1=1)" +
            " and if(?3 is not null,e.event_time >=?3,1=1)" +
            " and if(?4 is not null,e.event_period =?4,1=1)" ,nativeQuery = true)
    public List<EventEntity>  findAllEventEntitiesNumberByCondition( String eventId,
                                                       String eventType,
                                                       String eventTime,
                                                       int eventPeriod);

    /**
     *  通过事件Id得到事件对象
     * @param eventId 事件Id
     * @return 返回封装好对象的实体
     */
    @Query("select e from EventEntity e where e.eventId =?1")
    public EventEntity findEventByEventId(String eventId);



    /**
     * 事件处理完成的时候，把事件完成的时间设置回去
     * @param date 当前时间
     * @param eventId 事件的id
     */
    @Modifying
    @Query("update EventEntity e set e.endTime =?1 where e.eventId=?2")
    public int changeDate(String date,String eventId);

    /**
     * 改变事件受伤人数，灾害等级，事件进度
     * @param eventId
     * @param hurtPopulation
     * @param eventLevel
     * @param eventPeriod
     */
    @Modifying
    @Query(value = "update t_event as t set " +
            " t.event_level =?3," +
            " t.event_period=?4," +
            " t.hurt_population=?2 " +
            " where t.event_id=?1",nativeQuery = true)
    public int updateEventData(String eventId,String hurtPopulation,String eventLevel,int eventPeriod);


    @Query(value = "update t_event as t set " +
            " t.event_period =?2 " +
            " where t.event_id=?1",nativeQuery = true)
    public int updateEventPeriod(String eventId,int eventPeriod);
}
