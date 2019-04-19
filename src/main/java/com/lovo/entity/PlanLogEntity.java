package com.lovo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 预案操作日志 ：对每个预案的操作，都会生成一条日志。
 *                 需要记录的有，操作人、操作时间、操作类型（int）、预案编号
 * author 刘金林
 */
@Entity
@Table(name = "t_plan_log")
public class PlanLogEntity {

    private String pLogId; //预案管理日志编号。有意义的id，由时间戳+操作+随机数组成

    private String operator; //操作人

    private String operateTime; //操作时间

    private int operateType; //操作类型，增加删除修改

    private String planId; //预案编号

    @Id
    @Column(length = 48)
    public String getpLogId() {
        return pLogId;
    }

    public void setpLogId(String pLogId) {
        this.pLogId = pLogId;
    }
    @Column(length = 32)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    @Column(length = 48)
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
    @Column(length = 8)
    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }
    @Column(length = 48)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
