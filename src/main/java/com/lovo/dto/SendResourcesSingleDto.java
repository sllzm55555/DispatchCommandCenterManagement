package com.lovo.dto;

public class SendResourcesSingleDto {

    /**
     * 派遣次数
     */
    private int time;

    /**
     * 派遣单位类型
     */
    private String deptType;
    /**
     * 派遣人数
     */
    private int polulation;
    /**
     * 派遣资源
     */
    private int resource;

    public SendResourcesSingleDto() {
    }

    public SendResourcesSingleDto(int time, String deptType, int polulation, int resource) {
        this.time = time;
        this.deptType = deptType;
        this.polulation = polulation;
        this.resource = resource;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public int getPolulation() {
        return polulation;
    }

    public void setPolulation(int polulation) {
        this.polulation = polulation;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
