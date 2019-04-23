package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import com.lovo.entity.ResubmitEntity;
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
    @Query(value = "select  r.event_level,r.hurt_population,t.event_type,r.report_desc from t_followup_report as r " +
            " inner join t_event as t on t.event_id = r.fk_event_entity_id" +
            " where t.event_period=?4 r.fk_event_entity_id=?1" +
            " order by r.fk_event_entity_id desc" +
            " limit ?2,?3",nativeQuery = true)
    public List<Object []> findResourceEntitiesByEventEntityId(String eventEntityId, int pageNo, int pageSize,int eventPeriod);


    /**
     * 得到一个事件对应的所有续报
     * @param eventEntityId 续报对应的事件Id
     * @return 返回对应的集合
     */
    @Query(value = "select * from t_followup_report as r " +
            " inner join t_event as t on t.event_id=r.fk_event_entity_id" +
            " where t.event_period=?2 and r.fk_event_entity_id =?1",nativeQuery = true)
    public List<Object []> getAllResourNumber(String eventEntityId,int eventPeriod);
}
