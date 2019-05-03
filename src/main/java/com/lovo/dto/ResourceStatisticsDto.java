package com.lovo.dto;

public class ResourceStatisticsDto {
    private String id;
    private String pRescuingNum;
    private String cVacantNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPRescuingNum() {
        return pRescuingNum;
    }

    public void setPRescuingNum(String pRescuingNum) {
        this.pRescuingNum = pRescuingNum;
    }

    public String getCVacantNum() {
        return cVacantNum;
    }

    public void setCVacantNum(String cVacantNum) {
        this.cVacantNum = cVacantNum;
    }
}
