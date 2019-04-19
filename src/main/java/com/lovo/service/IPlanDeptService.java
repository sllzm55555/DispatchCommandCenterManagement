package com.lovo.service;

import com.lovo.entity.PlanDeptEntity;

public interface IPlanDeptService {
    /**
     * 添加预案关系中间表
     * @param planDeptEntity
     * @return
     */
    public PlanDeptEntity savaPlanDept(PlanDeptEntity planDeptEntity);
}
