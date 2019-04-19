/**
 * 预案和预案单位中间表
 * 高升
 */
package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_plan_dept")
public class PlanDeptEntity {
    /**
     * 中间表id
     */
    private String pdId;

    /**
     * 预案类
     */
    private PlanEntity planEntity;

    /**
     * 单位类
     */
    private DeptEntity deptEntity;

    /**
     * 资源数
     */
    private String resource;
    /**
     * 人数
     */
    private String personNum;

    @Id
    @GenericGenerator(name = "pid", strategy = "uuid")
    @GeneratedValue(generator = "pid")
    @Column(name = "pd_id", length = 32)
    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }
    @ManyToOne
    @JoinColumn(name="plan_id")
    public PlanEntity getPlanEntity() {
        return planEntity;
    }

    public void setPlanEntity(PlanEntity planEntity) {
        this.planEntity = planEntity;
    }
    @ManyToOne
    @JoinColumn(name="dept_id")
    public DeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }



    @Column(name = "plan_resource", length = 32)
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }



    @Column(name = "plan_personNum", length = 32)
    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }
}
