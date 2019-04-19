package com.lovo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 事件记录日志 ：对每个事件的操作，都会生成一条日志。
 *                 需要记录的有，操作人、操作时间、操作类型（int）、事件编号
 * author 刘金林
 */
@Entity
@Table(name = "t_event_log")
public class EventLogEntity {
    private String eLogId; //事件管理日志编号。有意义的id，由时间戳+操作+随机数组成

    private String operator; //操作人

    private String operateTime; //操作时间

    private int operateType; //操作类型，增加删除修改

    private String eventId; //事件编号
    @Id
    @Column(length = 48)
    public String geteLogId() {
        return eLogId;
    }

    public void seteLogId(String eLogId) {
        this.eLogId = eLogId;
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
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
