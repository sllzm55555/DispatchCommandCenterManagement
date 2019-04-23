package com.lovo.dto;

import com.lovo.entity.PlanDeptEntity;

import java.util.List;

public class PlanDto {
    /**
     * 预案名称
     */
    private  String planName;
    /**
     * 预案类型
     */
    private String planType;
    /**
     * 预案等级
     */
    private String planLevel;
    /**
     * 相关单位
     */
    private List<PlanDeptEntity> planDeptEntities;
    /**
     * 预案详情
     */
    private String plandecp;

    public PlanDto() {
    }

    public PlanDto(String planName, String planType, String planLevel, List<PlanDeptEntity> planDeptEntities, String plandecp) {
        this.planName = planName;
        this.planType = planType;
        this.planLevel = planLevel;
        this.planDeptEntities = planDeptEntities;
        this.plandecp = plandecp;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanLevel() {
        return planLevel;
    }

    public void setPlanLevel(String planLevel) {
        this.planLevel = planLevel;
    }

    public List<PlanDeptEntity> getPlanDeptEntities() {
        return planDeptEntities;
    }

    public void setPlanDeptEntities(List<PlanDeptEntity> planDeptEntities) {
        this.planDeptEntities = planDeptEntities;
    }

    public String getPlandecp() {
        return plandecp;
    }

    public void setPlandecp(String plandecp) {
        this.plandecp = plandecp;
    }
}
