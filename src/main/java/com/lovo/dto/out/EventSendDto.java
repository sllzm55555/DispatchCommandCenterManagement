package com.lovo.dto.out;

import java.util.List;

public class EventSendDto {

    /**
     * 人员集合
     */
    private List<PersonDto> personDtos;

    /**
     * 车辆集合
     */
    private List<CarDto> carDtos;

    /**
     * 负责人
     */
    private PersonDto person;


    /**
     * 事件编号
     */
    private String id;

    /**
     * 派遣编号
     */
    private String requestId;

    public List<PersonDto> getPersonDtos() {
        return personDtos;
    }

    public void setPersonDtos(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
