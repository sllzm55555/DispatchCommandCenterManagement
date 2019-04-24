package com.lovo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
//设置数据库名字
@Table(name="t_area")
@Entity
public class AreaEntity {

    /**
     * 区域id
     * id使用uuid方法
     * 设置id 字段名
     */
    @Id
    @GenericGenerator(name="aid",strategy = "uuid")
    @GeneratedValue(generator = "aid")
    @Column(name="area_id",length = 32)
    private String areaId;
    /**
     * 区域名字
     */
    @Column(name = "area_name",length=32)
    private String  areaName;
    /**
     * 区域包含的资源
     * 一对多关系
     * JsonIgnore   注解  是解决无限循环的问题  意思是在这里终止关联查询
     */
    @JsonIgnore
    @OneToMany(mappedBy = "areaEntity")
    private Set<ResourceEntity> setResource;


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Set<ResourceEntity> getSetResource() {
        return setResource;
    }

    public void setSetResource(Set<ResourceEntity> setResource) {
        this.setResource = setResource;
    }
}
