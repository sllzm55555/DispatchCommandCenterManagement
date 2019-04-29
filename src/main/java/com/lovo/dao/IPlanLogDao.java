package com.lovo.dao;

import com.lovo.entity.PlanLogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPlanLogDao  extends CrudRepository<PlanLogEntity, String> {

    /**
     * 分页查找满足情况的所有预案操作日志
     * @param  pageNum 页数
     * @param planId 条件中的事件编号
     * @param operateType 操作类型
     * @param operator 操作人
     * @param operateTime 操作时间
     * @return
     */
    @Query(value = "SELECT * FROM t_plan_log where " +
            " if(?1 is not null ,plan_id like concat('%',?1,'%'),1=1) " +
            " and if(?2 is not null ,operator like concat('%',?2,'%'),1=1)" +
            " and if(?3 is not null ,operate_time like CONCAT('%',?3,'%'),1=1)" +
            " and if(?4 is not null ,operate_type=?4 ,1=1)"+
            " order by operate_time desc limit ?5,?6",
            nativeQuery = true)
    public List<PlanLogEntity> showPlanLogListByPage(String planId,
                                                     String operator,
                                                     String operateTime,
                                                     Integer operateType,
                                                     Integer pageNum,
                                                     Integer pageSize);


    /**
     * 得到满足情况的所有日志数
     * @param planId 预案id
     * @param operateType 操作类型
     * @param operator 操作者
     * @param operateTime 操作时间
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_plan_log where if(?1 is not null ,plan_id=?1,1=1) " +
            " and if(?2 is not null ,operate_type=?2,1=1)" +
            " and if(?3 is not null ,operator=?3,1=1)" +
            " and if(?4 is not null ,operate_type=?4,1=1)",
            nativeQuery = true)
    public Integer getAllPlanLogCount(String planId, Integer operateType, String operator, String operateTime);

}
