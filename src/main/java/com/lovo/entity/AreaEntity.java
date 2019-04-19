package com.lovo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Table(name="t_area")
@Entity
public class AreaEntity {

    /**
     * 区域id
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
