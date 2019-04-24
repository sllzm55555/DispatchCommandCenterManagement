package com.lovo.dao;

import com.lovo.dto.ProgressDto;
import com.lovo.entity.SendProgressEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 资源派遣进度service
 * @date
 * @author lin
 */
public interface ISendProgressDao extends CrudRepository<SendProgressEntity,String> {

    /**
     * 通过事件Id查找该事件派出的所有资源个体Dto
     * @param eventId 事件ID
     * @return
     */
    @Query("SELECT new com.lovo.dto.ProgressDto(sp.resourceId,sp.deptName,sp.resourceName,sp.startTime,sp.returnTime) " +
            "FROM SendProgressEntity sp " +
            "left join sp.eventEntity e " +
            "where e.eventId=?1")
    public List<ProgressDto> findAllProgressByEventId(String eventId);

    /***
     * 通过事件ID和资源个体ID修改资源归队时间
     * @param returnTime 资源归队时间
     * @param eventId 事件ID
     * @param resourceId 资源个体ID
     */
    @Modifying
    @Query(value = "UPDATE t_send_progress sp " +
            "SET sp.return_time = ?1 " +
            "WHERE sp.resource_id=?2 " +
            "and sp.fk_event_id=?3 ", nativeQuery = true)
    public void updateReturnTimeByEventIdAndSendProgressId(String returnTime,String resourceId,String eventId);

}
