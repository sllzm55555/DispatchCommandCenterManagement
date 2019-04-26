package com.lovo.entity;

import com.lovo.dto.PlanFindDto;
import com.lovo.dto.ResubmitDto;
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
    private ResubmitDto resubmitDto;

    /***
     * 如果是续报，需要显示之前派遣的信息
     */
    private List<SendResourcesDto> sendResourcesDtoList;

    /**
     *预案模板
     */
    private List<PlanFindDto> planFindDtoList;

    public SendResourceBean() {
    }

    public EventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public ResubmitDto getResubmitDto() {
        return resubmitDto;
    }

    public void setResubmitDto(ResubmitDto resubmitDto) {
        this.resubmitDto = resubmitDto;
    }

    public List<SendResourcesDto> getSendResourcesDtoList() {
        return sendResourcesDtoList;
    }

    public void setSendResourcesDtoList(List<SendResourcesDto> sendResourcesDtoList) {
        this.sendResourcesDtoList = sendResourcesDtoList;
    }

    public List<PlanFindDto> getPlanFindDtoList() {
        return planFindDtoList;
    }

    public void setPlanFindDtoList(List<PlanFindDto> planFindDtoList) {
        this.planFindDtoList = planFindDtoList;
    }

    public SendResourceBean(EventEntity eventEntity, ResubmitDto resubmitDto, List<SendResourcesDto> sendResourcesDtoList, List<PlanFindDto> planFindDtoList) {
        this.eventEntity = eventEntity;
        this.resubmitDto = resubmitDto;
        this.sendResourcesDtoList = sendResourcesDtoList;
        this.planFindDtoList = planFindDtoList;
    }
}
