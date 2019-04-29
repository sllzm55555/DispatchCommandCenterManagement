package com.lovo.testDto;

import java.sql.Timestamp;

public class CarDto {
    /**
     * 车辆id
     */
    private String id;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 司机
     */
    private String driver;
    /**
     * 出发时间
     */
    private Timestamp startTime;
    /**
     * 归队时间
     */
    private Timestamp returnTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }
}
