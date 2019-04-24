package com.lovo.dto;

public class SendResourcesDto {

    /**
     * 匪警
     */
    private String deptTypeNature;

    /**
     * 匪警人数
     */
    private int naturePolulation;
    /**
     * 匪警车辆
     */
    private int natureResource;
    /**
     * 医院
     */
    private String deptTypeHospital;
    /**
     * 医院人数
     */
    private int hospitalPolulation;
    /**
     * 医院车辆
     */
    private int hospitalResource;
    /**
     * 火警
     */
    private String deptTypeFire;
    /**
     * 火警人数
     */
    private int firePolulation;
    /**
     * 火警车辆
     */
    private int fireResource;

    public String getDeptTypeNature() {
        return deptTypeNature;
    }

    public void setDeptTypeNature(String deptTypeNature) {
        this.deptTypeNature = deptTypeNature;
    }

    public int getNaturePolulation() {
        return naturePolulation;
    }

    public void setNaturePolulation(int naturePolulation) {
        this.naturePolulation = naturePolulation;
    }

    public int getNatureResource() {
        return natureResource;
    }

    public void setNatureResource(int natureResource) {
        this.natureResource = natureResource;
    }

    public String getDeptTypeHospital() {
        return deptTypeHospital;
    }

    public void setDeptTypeHospital(String deptTypeHospital) {
        this.deptTypeHospital = deptTypeHospital;
    }

    public int getHospitalPolulation() {
        return hospitalPolulation;
    }

    public void setHospitalPolulation(int hospitalPolulation) {
        this.hospitalPolulation = hospitalPolulation;
    }

    public int getHospitalResource() {
        return hospitalResource;
    }

    public void setHospitalResource(int hospitalResource) {
        this.hospitalResource = hospitalResource;
    }

    public String getDeptTypeFire() {
        return deptTypeFire;
    }

    public void setDeptTypeFire(String deptTypeFire) {
        this.deptTypeFire = deptTypeFire;
    }

    public int getFirePolulation() {
        return firePolulation;
    }

    public void setFirePolulation(int firePolulation) {
        this.firePolulation = firePolulation;
    }

    public int getFireResource() {
        return fireResource;
    }

    public void setFireResource(int fireResource) {
        this.fireResource = fireResource;
    }
}
