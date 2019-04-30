package com.lovo.dao;

import com.lovo.dto.ProgressDto;
import com.lovo.dto.SendResourceDto;

import java.util.List;

public class ShowDto {

    private List<ProgressDto> progressDtoList;

    private List<SendResourceDto> sendResourceDtoList;

    public ShowDto() {
    }

    public ShowDto(List<ProgressDto> progressDtoList, List<SendResourceDto> sendResourceDtoList) {
        this.progressDtoList = progressDtoList;
        this.sendResourceDtoList = sendResourceDtoList;
    }

    public List<ProgressDto> getProgressDtoList() {
        return progressDtoList;
    }

    public void setProgressDtoList(List<ProgressDto> progressDtoList) {
        this.progressDtoList = progressDtoList;
    }

    public List<SendResourceDto> getSendResourceDtoList() {
        return sendResourceDtoList;
    }

    public void setSendResourceDtoList(List<SendResourceDto> sendResourceDtoList) {
        this.sendResourceDtoList = sendResourceDtoList;
    }
}
