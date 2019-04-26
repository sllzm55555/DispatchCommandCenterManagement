package com.lovo.dto;

/**
 * 预案里面有的资源，具体需要多少人，多少车
 */
public class DeptDto {

    //需要的的单位
    private  String deptName;
// 需要的车辆数
    private String carNumber;
//需要的人数
    private String personNumber;

    public DeptDto() {
    }

    public DeptDto(String deptName, String carNumber, String personNumber) {
        this.deptName = deptName;
        this.carNumber = carNumber;
        this.personNumber = personNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
}
