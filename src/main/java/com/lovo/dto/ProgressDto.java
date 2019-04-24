package com.lovo.dto;

/***
 * 进度DTO
 * @author lin
 */
public class ProgressDto {

    /***
     * 单个资源进度id
     */
    private String ProgressId;

    /***
     * 单位名称
     */
    private String deptName;

    /***
     * 人名或车牌
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public ProgressDto(String progressId, String deptName, String resourceName, String startTime, String returnTime) {
        ProgressId = progressId;
        this.deptName = deptName;
        this.resourceName = resourceName;
        this.startTime = startTime;
        this.returnTime = returnTime;
    }

    public ProgressDto() {
    }

    public String getProgressId() {
        return ProgressId;
    }

    public void setProgressId(String progressId) {
        ProgressId = progressId;
    }
}
