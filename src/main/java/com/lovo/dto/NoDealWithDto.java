package com.lovo.dto;

/**
 * 未处理事件的Dto
 * @author 阿枫
 * @date 2019-04-22
 */
public class NoDealWithDto {
    /**
     * eventId 事件编号
     */
    private String eventId;

    /**
     * eventName 事件名称
     */
    private String eventName;

    /**
     * eventType 事件类型
     */
    private String eventType;

    /**
     *eventLevel 事件等级
     */
    private String eventLevel;

    /**
     *eventArea 事件区域
     */
    private String eventArea;

    /**
     *eventCasualties 伤亡人数
     */
    private int hurtPopulation;

    /**
     *eventReporter 报警人
     */
    private String alarmPerson;

    /**
     *alarmTel 联系方式
     */
    private String alarmTel;

    /**
     *  alarmAddress 详细地址
     */
    private String alarmAddress;

    /**
     * eventUploadPeople 上报人
     */
    private String eventUploadPeople;

    /**
     * eventTime 上报时间
     */
    private String eventTime;


    /**
     * eventPeriod 事件阶段
     * 1、未处理阶段
     * 2、处理中阶段
     * 3、处理完成阶段
     */
    private int eventPeriod;

    /**
     * uniqueAttr 特殊属性
     */
    private String uniqueAttr;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getEventArea() {
        return eventArea;
    }

    public void setEventArea(String eventArea) {
        this.eventArea = eventArea;
    }

    public int getHurtPopulation() {
        return hurtPopulation;
    }

    public void setHurtPopulation(int hurtPopulation) {
        this.hurtPopulation = hurtPopulation;
    }

    public String getAlarmPerson() {
        return alarmPerson;
    }

    public void setAlarmPerson(String alarmPerson) {
        this.alarmPerson = alarmPerson;
    }

    public String getAlarmTel() {
        return alarmTel;
    }

    public void setAlarmTel(String alarmTel) {
        this.alarmTel = alarmTel;
    }

    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }

    public String getEventUploadPeople() {
        return eventUploadPeople;
    }

    public void setEventUploadPeople(String eventUploadPeople) {
        this.eventUploadPeople = eventUploadPeople;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public int getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(int eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public String getUniqueAttr() {
        return uniqueAttr;
    }

    public void setUniqueAttr(String uniqueAttr) {
        this.uniqueAttr = uniqueAttr;
    }
}
