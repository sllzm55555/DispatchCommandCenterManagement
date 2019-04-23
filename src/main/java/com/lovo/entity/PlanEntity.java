/**
 * 预案实体
 * 高升
 */
package com.lovo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_plan")
public class PlanEntity {
    /**
     * 预案id
     */
    private String planId;
    /**
     * 预案名称
     */
    private String planName;
    /**
     * 事件类型
     */
    private String enevType;
    /**
     * 事件级别
     */
    private String enevLeve;
    /**
     * 预案详情
     */
    private String planDecs;


    private Set<PlanDeptEntity> setPlanDeptEntity;

    @Id
    @GenericGenerator(name = "pid", strategy = "uuid")
    @GeneratedValue(generator = "pid")
    @Column(name = "plan_id", length = 32)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    @Column(name = "plan_name", length = 32)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
    @Column(name = "event_type", length = 32)
    public String getEnevType() {
        return enevType;
    }

    public void setEnevType(String enevType) {
        this.enevType = enevType;
    }
    @Column(name = "event_level", length = 32)
    public String getEnevLeve() {
        return enevLeve;
    }

    public void setEnevLeve(String enevLeve) {
        this.enevLeve = enevLeve;
    }
    @Column(name = "plan_desc", length = 32)
    public String getPlanDecs() {
        return planDecs;
    }

    public void setPlanDecs(String planDecs) {
        this.planDecs = planDecs;
    }

    @OneToMany(mappedBy="planEntity")
    @JsonIgnore
    public Set<PlanDeptEntity> getSetPlanDeptEntity() {
        return setPlanDeptEntity;
    }

    public void setSetPlanDeptEntity(Set<PlanDeptEntity> setPlanDeptEntity) {
        this.setPlanDeptEntity = setPlanDeptEntity;
    }
}
