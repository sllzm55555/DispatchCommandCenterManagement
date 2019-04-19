/**
 * 单位实体
 * 高升
 */
package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_dept")

public class DeptEntity {
    /**
     * 单位id
     */
    private String deptId;
    /**
     * 单位名称
     */
    private String deptName;

    /**
     * 一对多获取预案
     */
    private Set<PlanDeptEntity> setPlanDeptEntity;

    @Id
    @GenericGenerator(name = "pid", strategy = "uuid")
    @GeneratedValue(generator = "pid")
    @Column(name = "dept_id", length = 32)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    @Column(name = "dept_name", length = 32)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    @OneToMany(mappedBy="deptEntity")
    public Set<PlanDeptEntity> getSetPlanDeptEntity() {
        return setPlanDeptEntity;
    }

    public void setSetPlanDeptEntity(Set<PlanDeptEntity> setPlanDeptEntity) {
        this.setPlanDeptEntity = setPlanDeptEntity;
    }
}
