package com.lovo.entity;


import javax.persistence.*;

/**
 *  派遣资源实体：派遣资源记录表
 * @author 老妖怪
 * @date
 */
@Entity
@Table(name = "t_send_resource")
public class SendResourceEntity {
    /**
     * requestId 派遣编号
     */
    private String requestId;

    /**
     *
     * eventId  事件编号
     */
    private String eventId;

    /**
     *
     * deptId 单位编号
     */
    private String deptId;

    /**
     * requestPopulation 派遣人数
     *
     */
    private int requestPopulation;

    /**
     * requestResource 派遣资源
     *
     */
    private int requestResource;

    /**
     * requestTimes 第几次续派
     *0代表是第一次派遣
     * 1第一次续派
     */
    private int requestTimes;

    /**
     *
     * chargeName 负责人
     */
    private String chargeName;

    /**
     *
     * chargeTel 负责人电话
     */
    private String chargeTel;


    /**
     *
     * 事件对象
     */
    private EventEntity eventEntity;

    /**
     * 单位对象
     */
    private DeptEntity deptEntity;

    @ManyToOne
    @JoinColumn(name = "fk_dept_id")
    public DeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    @ManyToOne
    @JoinColumn(name = "fk_event_id")
    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }


    @Column(length = 13)
    public String getChargeTel() {
        return chargeTel;
    }

    public void setChargeTel(String chargeTel) {
        this.chargeTel = chargeTel;
    }

    @Column(length = 36)
    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public int getRequestTimes() {
        return requestTimes;
    }

    public void setRequestTimes(int requestTimes) {
        this.requestTimes = requestTimes;
    }

    public int getRequestResource() {
        return requestResource;
    }

    public void setRequestResource(int requestResource) {
        this.requestResource = requestResource;
    }

    public int getRequestPopulation() {
        return requestPopulation;
    }

    public void setRequestPopulation(int requestPopulation) {
        this.requestPopulation = requestPopulation;
    }

    @Column(length = 32)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(length = 32)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Id
    @Column(length = 32)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


}
