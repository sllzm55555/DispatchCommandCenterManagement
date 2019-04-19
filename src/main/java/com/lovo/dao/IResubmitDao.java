package com.lovo.dao;

import com.lovo.entity.ResourceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IResubmitDao extends CrudRepository<ResourceEntity,String> {

    /**
     * 通过事件的Id，得到事件对应的所有续报
     * @param eventEntityId 事件Id
     * @return 一个事件对应的续报集合
     */
    @Query(value = "select * from t_followup_report as r where r.fk_event_entity_id=?1" +
            " order by r.fk_event_entity_id desc " +
            " limit ?2,?3",nativeQuery = true)
    public List<ResourceEntity> findResourceEntitiesByEventEntityId(String eventEntityId,int pageNo,int pageSize);
}
