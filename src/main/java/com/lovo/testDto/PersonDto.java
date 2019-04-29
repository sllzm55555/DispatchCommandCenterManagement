package com.lovo.testDto;

import java.sql.Timestamp;

public class PersonDto {
    /**
     * 人员id
     */
    private String id;
    /**
     * 人员名字
     */
    private String personName;
    /**
     * 人员电话
     */
    private String tel;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
