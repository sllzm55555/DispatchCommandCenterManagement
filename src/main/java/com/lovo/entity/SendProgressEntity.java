package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

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
     *deptName 单位名称
     */
    private String deptName;

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
     * resourceId 资源编号(人的id或者车的id)
     */
    private String resourceId;

    /**
     * 表示对应的某次派遣对应的单位实体
     */
    private SendResourceEntity sendResourceEntity;

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

    @Column(length = 32)
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @ManyToOne
    @JoinColumn(name = "fk_resourceId")
    public SendResourceEntity getSendResourceEntity() {
        return sendResourceEntity;
    }

    public void setSendResourceEntity(SendResourceEntity sendResourceEntity) {
        this.sendResourceEntity = sendResourceEntity;
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
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    @Id
    @GenericGenerator(name="spid",strategy = "uuid")
    @GeneratedValue(generator = "spid")
    @Column(length = 32)
    public String getSendProgressId() {
        return sendProgressId;
    }

    public void setSendProgressId(String sendProgressId) {
        this.sendProgressId = sendProgressId;
    }
}
