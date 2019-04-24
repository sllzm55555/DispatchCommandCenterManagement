package com.lovo.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *  续报实体
 * @author 老妖怪
 * @date
 */
@Entity
@Table(name = "t_followup_report")
public class ResubmitEntity {
    /**
     *  reportId 续报编号
     */

    private String reportId;

    /**
     * eventId 续报对应的事件实体
     */
    private EventEntity eventEntity;

    /**
     * eventLevel 改变的事件等级
     */
    private String eventLevel;

    /**
     *hurtPopulation 总共伤亡人数
     *
     */
    private String hurtPopulation;

    /**
     *
     * reportDesc 续报详情
     */
    private String reportDesc;

    /**
     *  reportPeople 续报人
     */
    private String reportPeople;

    /**
     *reportTel 续报人电话
     */
    private String reportTel;
    /**
     * 续报的时间
     */
    private String reportTime;

    /**
     * 续报的状态
     */
    private String reportPeriod;

    @Column(columnDefinition = "datetime")
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    @Column(length = 32)
    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    @Id
    @Column(length = 32)
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @ManyToOne()
    @JoinColumn(name = "fk_eventEntityId")
    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getHurtPopulation() {
        return hurtPopulation;
    }

    public void setHurtPopulation(String hurtPopulation) {
        this.hurtPopulation = hurtPopulation;
    }

    @Column(length = 255)
    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    @Column(length = 16)
    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }
    @Column(length = 13)
    public String getReportTel() {
        return reportTel;
    }

    public void setReportTel(String reportTel) {
        this.reportTel = reportTel;
    }
}
