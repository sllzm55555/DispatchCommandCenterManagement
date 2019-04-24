package com.lovo.dao;

import com.lovo.dto.SendResourceDto;
import com.lovo.entity.SendResourceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/***
 * 单位一次派遣记录dao
 * @author lin
 * @date
 */
public interface ISendResourceDao extends CrudRepository<SendResourceEntity,String> {

    /***
     * 通过事件ID查找该事件所派出的所有sendResource信息
     * @param eventId   事件ID
     * @return
     */
    @Query("select new com.lovo.dto.SendResourceDto(sr.requestId,sr.resourceName,sr.requestPopulation,sr.requestResource,sr.requestTimes,sr.chargeName,sr.chargeTel) " +
            "FROM SendResourceEntity sr " +
            "left join sr.eventEntity e " +
            "where e.eventId=?1")
    public List<SendResourceDto> findAllSendResourceByEventId(String eventId);
}
