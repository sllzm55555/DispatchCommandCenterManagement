package com.lovo.dto;

import com.lovo.entity.PlanDeptEntity;

import java.util.List;

public class PlanFindDto {

    /***
     * 预案id
     */
    private String planId;

    /**
     * 预案名称
     */
    private String planName;
    /**
     * 预案类型
     */
    private String planType;
    /**
     * 预案等级
     */
    private String planLevel;

    /**
     * 预案对应的单位
     */
    List<DeptDto> deptDtos;

    /**
     * 预案详情
     */
    private String plandecp;

    public PlanFindDto() {
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public PlanFindDto(String planId, String planName, String planType, String planLevel, List<DeptDto> deptDtos, String plandecp) {
        this.planId = planId;
        this.planName = planName;
        this.planType = planType;
        this.planLevel = planLevel;
        this.deptDtos = deptDtos;
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

    public List<DeptDto> getDeptDtos() {
        return deptDtos;
    }

    public void setDeptDtos(List<DeptDto> deptDtos) {
        this.deptDtos = deptDtos;
    }

    public String getPlandecp() {
        return plandecp;
    }

    public void setPlandecp(String plandecp) {
        this.plandecp = plandecp;
    }
}
