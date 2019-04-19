package com.lovo.dao;

import com.lovo.entity.PlanLogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPlanLogDao  extends CrudRepository<PlanLogEntity, String> {
    /**
     * 分页查找满足情况的所有预案操作日志
     * @param pageable
     * @param planId
     * @param operateType
     * @param operator
     * @param operateTime
     * @return
     */
    @Query("select p from PlanLogEntity p ")
    public List<PlanLogEntity> getPlanLogListByPage(Pageable pageable, String planId, String operateType, String operator, String operateTime);
}
