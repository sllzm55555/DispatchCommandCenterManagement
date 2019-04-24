package com.lovo.entity;


import javax.persistence.*;

/**
 *  派遣资源实体：派遣资源记录表(单个单位一次派遣)
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
     * resourceName 单位名称
     */
    private String resourceName;

    /**
     * resourceUrl 单位url（用来确定派遣单位）
     */
    private String resourceUrl;

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
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(length = 48)
    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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
