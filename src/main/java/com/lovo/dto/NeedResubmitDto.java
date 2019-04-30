package com.lovo.dto;

import com.lovo.entity.EventEntity;

public class NeedResubmitDto {

    /**
     *  reportId 续报编号
     */

    private String reportId;

    /**
     * eventId 续报对应的事件id
     */
    private String eventId;
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

    public NeedResubmitDto() {
    }

    public NeedResubmitDto(String reportId, String eventId, String eventLevel, String hurtPopulation, String reportDesc, String reportPeople, String reportTel, String reportTime) {
        this.reportId = reportId;
        this.eventId = eventId;
        this.eventLevel = eventLevel;
        this.hurtPopulation = hurtPopulation;
        this.reportDesc = reportDesc;
        this.reportPeople = reportPeople;
        this.reportTel = reportTel;
        this.reportTime = reportTime;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    public String getReportTel() {
        return reportTel;
    }

    public void setReportTel(String reportTel) {
        this.reportTel = reportTel;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}
