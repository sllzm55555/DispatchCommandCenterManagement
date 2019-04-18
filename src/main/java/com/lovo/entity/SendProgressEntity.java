package com.lovo.entity;

import javax.persistence.*;
/**
 *  派遣资源实体：派遣资源的进度记录表（表示单个车或者人）
 *  查所有的通过事件编号可以查到所有人的是否在现场的进度
 * @author 老妖怪
 * @date
 */
@Entity
@Table(name = "t_send_progress")
public class SendProgressEntity {
    /**
     * sendProgressId 主键id
     */
    private String sendProgressId;

    /**
     *deptId 单位编号
     */
    private String deptId;

    /**
     * resourceName 资源名称（人名字或者车牌）
     */
    private String resourceName;

    /**
     *startTime 出发时间
     */
    private String startTime;

    /**
     *returnTime 归队时间
     */
    private String returnTime;

    /**
     * resoutceId 资源编号(人的id或者车的id)
     */
    private String resoutceId;

    /**
     * 表示对应的某次派遣对应的单位实体
     */
    private SendResourseEntity sendResourseEntity;

    /**
     * 表示单个的人属于那次的派遣事件
     */
    private EventEntity eventEntity;

    @ManyToOne
    @JoinColumn(name = "fk_eventId")
    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public String getResoutceId() {
        return resoutceId;
    }

    public void setResoutceId(String resoutceId) {
        this.resoutceId = resoutceId;
    }

    @ManyToOne
    @JoinColumn(name = "fk_resourseId")
    public SendResourseEntity getSendResourseEntity() {
        return sendResourseEntity;
    }

    public void setSendResourseEntity(SendResourseEntity sendResourseEntity) {
        this.sendResourseEntity = sendResourseEntity;
    }

    @Column(columnDefinition="TIMESTAMP")
    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Column(columnDefinition="TIMESTAMP")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(length = 16)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(length = 32)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }


    @Id
    @Column(length = 32)
    public String getSendProgressId() {
        return sendProgressId;
    }

    public void setSendProgressId(String sendProgressId) {
        this.sendProgressId = sendProgressId;
    }
}
