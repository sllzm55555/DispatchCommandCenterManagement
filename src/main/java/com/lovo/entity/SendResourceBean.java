package com.lovo.entity;

import com.lovo.dto.SendResourcesDto;

import java.util.List;

/***
 * 选择派遣页面显示Bean
 * @param <>
 */
public class SendResourceBean {

    /**
     * 事件实体
     */
    private EventEntity eventEntity;

    /***
     * 最后一次续报实体
     */
    private ResubmitEntity resubmitEntity;

    /***
     * 如果是续报，需要显示之前派遣的信息
     */
    private List<SendResourcesDto> sendResourcesDtoList;

    /**
     *预案模板
     */
    private List<PlanDeptEntity> planDeptList;

    public SendResourceBean() {
    }

    public SendResourceBean(EventEntity eventEntity, ResubmitEntity resubmitEntity, List<SendResourcesDto> sendResourcesDtoList, List<PlanDeptEntity> planDeptList) {
        this.eventEntity = eventEntity;
        this.resubmitEntity = resubmitEntity;
        this.sendResourcesDtoList = sendResourcesDtoList;
        this.planDeptList = planDeptList;
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public ResubmitEntity getResubmitEntity() {
        return resubmitEntity;
    }

    public void setResubmitEntity(ResubmitEntity resubmitEntity) {
        this.resubmitEntity = resubmitEntity;
    }

    public List<SendResourcesDto> getSendResourcesDtoList() {
        return sendResourcesDtoList;
    }

    public void setSendResourcesDtoList(List<SendResourcesDto> sendResourcesDtoList) {
        this.sendResourcesDtoList = sendResourcesDtoList;
    }

    public List<PlanDeptEntity> getPlanDeptList() {
        return planDeptList;
    }

    public void setPlanDeptList(List<PlanDeptEntity> planDeptList) {
        this.planDeptList = planDeptList;
    }
}
