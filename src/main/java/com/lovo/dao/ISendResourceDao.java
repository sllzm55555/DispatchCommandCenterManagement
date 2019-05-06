package com.lovo.dao;

import com.lovo.dto.SendResourceDto;
import com.lovo.dto.SendResourcesSingleDto;
import com.lovo.entity.SendResourceEntity;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * 通过事件id得到一个事件派遣DTO集合
     * @param eventId 事件id
     * @return
     */
    @Query(value = "SELECT" +
            " sr.request_times,r.dept_type,sr.request_population,sr.request_resource" +
            " FROM" +
            " t_send_resource sr" +
            " LEFT JOIN t_resource r ON r.dept_url = sr.resource_url" +
            " LEFT JOIN t_event e ON e.event_id = sr.fk_event_id" +
            " WHERE " +
            " r.dept_url = sr.resource_url" +
            " AND e.event_id = ?1" +
            " ORDER BY\n" +
            " sr.request_times ASC",nativeQuery = true)
    List<Object[]> getSendResourcesListByEventId(String eventId);

    /**
     * 通过事件id，派遣编号修改单条派遣信息的负责人和负责人电话
     * @param eventId
     * @return
     */
    @Modifying
    @Query(value = "UPDATE t_send_resource sr SET sr.charge_name=?1,sr.charge_tel =?2 " +
            "WHERE request_id=?4 and fk_event_id =?3",nativeQuery = true)
    public int updateByEventEntity_EventIdAndRequestId(String chargeName,String chargeTel,String eventId, String requestId);

    /**
     * 通过事件id和派遣编号的到资源派遣信息
     * @param eventId
     * @return
     */
    public SendResourceEntity findByEventEntity_EventId(String eventId/*, String requestId*/);

    public SendResourceEntity findByEventEntity_EventIdAndRequestId(String eventId, String requestId);

    /**
     * 找到该事件的当前派遣次数
     * @param eventId
     * @return
     */
    @Query(value = "SELECT * FROM t_send_resource sr WHERE sr.fk_event_id =?1 ORDER BY sr.request_times DESC limit 0,1",nativeQuery = true)
    public SendResourceEntity findMaxRequestTime(String eventId);
}
