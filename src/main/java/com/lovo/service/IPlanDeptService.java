package com.lovo.service;

import com.lovo.entity.PlanDeptEntity;
import com.lovo.entity.PlanEntity;

import java.util.List;

public interface IPlanDeptService {
    /**
     * 添加预案关系中间表
     * @param planDeptEntity
     * @return
     */
    public PlanDeptEntity savaPlanDept(PlanDeptEntity planDeptEntity);

    /**
     * 删除预案
     * @param planid
     */
    public void deleteplanbyid(String planid);

    /**
     * 取得单个预案模板
     * @param EvenType 事件类型
     * @param EvenLive 事件等级
     * @return
     */
    public List<PlanDeptEntity> getPlanDeptEntitiesByPlan(String EvenType, String EvenLive);

    /**
     * 预案详情查询
     * @param PlanId
     * @return
     */
    public List<PlanDeptEntity> getPlanDeptByPlanId(String PlanId);

    /**
     * 修改中间表
     * @param pdid
     * @param personnum
     * @param planresource
     * @return
     */
    public Integer updatePlanDeptEntitiesByPlanId(String pdid,String personnum,String planresource);
}
