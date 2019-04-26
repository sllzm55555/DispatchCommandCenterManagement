package com.lovo.dto;

import com.lovo.entity.ResourceEntity;

import java.util.List;

/**
 * 一个地址上的所有110list，120list，119list
 */
public class AreaDto {
    private List<ResourceEntity> gongan;
    private List<ResourceEntity> xiaofang;
    private List<ResourceEntity> yiyuan;

    public AreaDto(List<ResourceEntity> gongan, List<ResourceEntity> xiaofang, List<ResourceEntity> yiyuan) {
        this.gongan = gongan;
        this.xiaofang = xiaofang;
        this.yiyuan = yiyuan;
    }

    public AreaDto() {
    }

    public List<ResourceEntity> getGongan() {
        return gongan;
    }

    public void setGongan(List<ResourceEntity> gongan) {
        this.gongan = gongan;
    }

    public List<ResourceEntity> getXiaofang() {
        return xiaofang;
    }

    public void setXiaofang(List<ResourceEntity> xiaofang) {
        this.xiaofang = xiaofang;
    }

    public List<ResourceEntity> getYiyuan() {
        return yiyuan;
    }

    public void setYiyuan(List<ResourceEntity> yiyuan) {
        this.yiyuan = yiyuan;
    }
}
