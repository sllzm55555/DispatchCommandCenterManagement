package com.lovo.dto;

/***
 * 单个单位一次派遣Dto
 */
public class SendResourceDto {
    /**
     * requestId 派遣编号
     */
    private String requestId;

    /**
     * resourceName 单位名称
     */
    private String resourceName;

    /***
     * 定位到单位的英文名(url)
     */
    private String resourceUrl;

    /**
     * requestPopulation 派遣人数
     */
    private int requestPopulation;

    /**
     * requestResource 派遣资源
     */
    private int requestResource;

    /**
     * requestTimes 第几次续派
     * 0代表是第一次派遣
     * 1第一次续派
     */
    private int requestTimes;

    /**
     * chargeName 负责人
     */
    private String chargeName;

    /**
     * chargeTel 负责人电话
     */
    private String chargeTel;

    public SendResourceDto(String requestId, String resourceName, int requestPopulation, int requestResource, int requestTimes, String chargeName, String chargeTel) {
        this.requestId = requestId;
        this.resourceName = resourceName;
        this.requestPopulation = requestPopulation;
        this.requestResource = requestResource;
        this.requestTimes = requestTimes;
        this.chargeName = chargeName;
        this.chargeTel = chargeTel;
    }

    public SendResourceDto() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getRequestPopulation() {
        return requestPopulation;
    }

    public void setRequestPopulation(int requestPopulation) {
        this.requestPopulation = requestPopulation;
    }

    public int getRequestResource() {
        return requestResource;
    }

    public void setRequestResource(int requestResource) {
        this.requestResource = requestResource;
    }

    public int getRequestTimes() {
        return requestTimes;
    }

    public void setRequestTimes(int requestTimes) {
        this.requestTimes = requestTimes;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getChargeTel() {
        return chargeTel;
    }

    public void setChargeTel(String chargeTel) {
        this.chargeTel = chargeTel;
    }
}
