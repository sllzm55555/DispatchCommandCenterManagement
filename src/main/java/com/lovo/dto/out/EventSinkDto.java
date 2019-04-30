package com.lovo.dto.out;

import java.sql.Timestamp;

public class EventSinkDto {
    /**
     * 事件编号
     */
    private String id;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 发生区域
     */
    private String eventArea;
    /**
     * 发生时间
     */
    private Timestamp eventTime;
    /**
     * 报警人
     */
    private String alarmPerson;
    /**
     * 报警人电话
     */
    private String alarmTel;
    /**
     * 详细地址
     */
    private String alarmAddress;
    /**
     * 派遣编号
     */
    private String requestId;
    /**
     * 需要派出人员数量
     */
    private int personNum;
    /**
     * 需要派出车辆数量
     */
    private int carNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEventArea() {
        return eventArea;
    }

    public void setEventArea(String eventArea) {
        this.eventArea = eventArea;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public EventSinkDto() {
        super();
    }

    public EventSinkDto(String id, String eventName, String eventType, String eventArea, Timestamp eventTime, String alarmPerson, String alarmTel, String alarmAddress, String requestId, int personNum, int carNum) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventArea = eventArea;
        this.eventTime = eventTime;
        this.alarmPerson = alarmPerson;
        this.alarmTel = alarmTel;
        this.alarmAddress = alarmAddress;
        this.requestId = requestId;
        this.personNum = personNum;
        this.carNum = carNum;
    }
    //负责人名字和电话，
    /*private PersonnelEntity personnelEntity;*/
    //资源list
    //人和车的
    //车牌号（人名）    车辆ID（人id）   出发时间  归队时间
}
