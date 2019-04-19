package com.lovo.dto;


/**
 * 从未处理事件详情页面跳转到资源派遣页面，需要事件类型，事件等级，事发区域，三个字段
 *
 * 从未处理事件详情页面到资源调遣的Dto
 * @author 阿枫
 * @date 2019-04-19
 */
public class EventDto {

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 事件等级
     */
    private String eventLevel;

    /**
     * 事发区域
     */
    private String eventArea;

    public EventDto() {
    }


    public EventDto(String eventType, String eventLevel, String eventArea) {
        this.eventType = eventType;
        this.eventLevel = eventLevel;
        this.eventArea = eventArea;
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
}
