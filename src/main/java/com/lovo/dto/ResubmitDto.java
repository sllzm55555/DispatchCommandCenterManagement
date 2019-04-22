package com.lovo.dto;

/**
 * 续报的Dto
 */
public class ResubmitDto {
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 改变后的等级
     */
    private String eventLevel;
    /**
     * 共计伤亡人数
     */
    private String hurtPopulation;
    /**
     * 详情
     */
    private String reportDesc;

    public ResubmitDto() {
    }

    public ResubmitDto(String eventType, String eventLevel, String hurtPopulation, String reportDesc) {
        this.eventType = eventType;
        this.eventLevel = eventLevel;
        this.hurtPopulation = hurtPopulation;
        this.reportDesc = reportDesc;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
}
