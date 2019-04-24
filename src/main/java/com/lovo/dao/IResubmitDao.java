package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import com.lovo.entity.ResubmitEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 续报的Dao
 * @author 阿枫
 * @date 2019-04-19
 */
public interface IResubmitDao extends CrudRepository<ResourceEntity,String> {


    /**
     * 得到一个事件对应的续报，按分页显示
     * @param eventEntityId 事件的Id
     * @param pageNo 当前的页码
     * @param pageSize 每页显示的条数
     * @return 返回集合
     */
    @Query(value = "select  r.event_level,r.hurt_population,t.event_type,r.report_desc " +
            " from t_followup_report as r " +
            " inner join t_event as t on t.event_id = r.fk_event_entity_id" +
            " where if(?4 is not null,t.event_period=?4,1=1) and " +
            " if(?1 is not null,r.fk_event_entity_id=?1,1=1)" +
            " limit ?2,?3",nativeQuery = true)
    public List<Object []> findResourceEntitiesByEventEntityId(String eventEntityId, int pageNo, int pageSize,int eventPeriod);


    /**
     * 得到一个事件对应的所有续报
     * @param eventEntityId 续报对应的事件Id
     *  @param eventPeriod 事件的进度
     * @return 返回对应的集合
     */
    @Query(value = "select r.event_level,r.hurt_population,t.event_type,r.report_desc" +
            " from t_followup_report as r " +
            " inner join t_event as t on t.event_id=r.fk_event_entity_id" +
            " where if(?2 is not null,t.event_period=?2,1=1)" +
            " and if(?1 is not null,r.fk_event_entity_id =?1,1=1)" +
            " order by r.report_time",nativeQuery = true)
    public List<Object []> getAllResourNumber(String eventEntityId,int eventPeriod);

    /**
     * 点击未处理事件，进行处理完成之后，把之前的续报状态全部改成已处理
     * 1，表示未处理，2，表示已经处理
     * @param eventId 事件的Id
     */
    @Modifying
    @Query(value = "update t_followup_report as t set t.report_period=2 " +
            " where  t.fk_event_entity_id = ?1  ",nativeQuery = true)
    public void changeResubmitPeriod(String eventId);



}
