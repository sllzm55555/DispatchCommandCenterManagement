/**
 * 预案主页接口
 * 高升
 */
package com.lovo.service;

import com.lovo.entity.PlanEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlanService {
    /**
     * 查询所有信息
     * @param plangrade 事件等级
     * @param planType 事件类型
     * @return
     */
    public List<PlanEntity> findAllPlanEntity(String plangrade, String planType ,Integer pageNum);

    /**
     * 添加预案
     * @param planEntity
     * @return
     */
    public PlanEntity savaPlanEntity(PlanEntity planEntity);

    /**
     * 查询最大页数
     * @param plangrade
     * @param planType
     * @return
     */
    public Integer findcount(String plangrade, String planType);
}
