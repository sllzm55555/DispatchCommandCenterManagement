package com.lovo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 *  事件实体
 * @author 老妖怪
 * @date
 */
@Entity
@Table(name = "t_event")
public class EventEntity {
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
     * endTime 结束时间
     */
    private String endTime;
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

    /**
     * 事件的单次派遣情况
     */
    private Set<SendResourceEntity> sendResourceEntitySet;

    /**
     * 查看一个事件所有的派遣进度，包含所有的（单个）车辆和人员的进度
     */
    private Set<SendProgressEntity> sendProgressEntities;


    /**
     * 一个事件有多个续报，包括处理中也可能存在多个续报
     */
    private Set<ResubmitEntity> resubmitEntities;

    @Column(columnDefinition = "datetime")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "eventEntity")
    @JsonIgnore
    public Set<ResubmitEntity> getResubmitEntities() {
        return resubmitEntities;
    }

    public void setResubmitEntities(Set<ResubmitEntity> resubmitEntities) {
        this.resubmitEntities = resubmitEntities;
    }

    @OneToMany(mappedBy = "eventEntity")
    @JsonIgnore
    public Set<SendProgressEntity> getSendProgressEntities() {
        return sendProgressEntities;
    }

    public void setSendProgressEntities(Set<SendProgressEntity> sendProgressEntities) {
        this.sendProgressEntities = sendProgressEntities;
    }

    @OneToMany(mappedBy = "eventEntity")
    @JsonIgnore
    public Set<SendResourceEntity> getSendResourceEntitySet() {
        return sendResourceEntitySet;
    }

    public void setSendResourceEntitySet(Set<SendResourceEntity> sendResourceEntitySet) {
        this.sendResourceEntitySet = sendResourceEntitySet;
    }

    @Id
    @Column(length = 32 )
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Column(length = 24)
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Column(length = 20)
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

    @Column(length = 32)
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

    @Column(length = 16)
    public String getAlarmPerson() {
        return alarmPerson;
    }

    public void setAlarmPerson(String alarmPerson) {
        this.alarmPerson = alarmPerson;
    }
    @Column(length = 13)
    public String getAlarmTel() {
        return alarmTel;
    }

    public void setAlarmTel(String alarmTel) {
        this.alarmTel = alarmTel;
    }
    @Column(length = 255)
    public String getAlarmAddress() {
        return alarmAddress;
    }

    public void setAlarmAddress(String alarmAddress) {
        this.alarmAddress = alarmAddress;
    }
    @Column(length = 16)
    public String getEventUploadPeople() {
        return eventUploadPeople;
    }

    public void setEventUploadPeople(String eventUploadPeople) {
        this.eventUploadPeople = eventUploadPeople;
    }

    @Column(columnDefinition = "datetime")
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
    @Column(length = 255)
    public String getUniqueAttr() {
        return uniqueAttr;
    }

    public void setUniqueAttr(String uniqueAttr) {
        this.uniqueAttr = uniqueAttr;
    }
}
