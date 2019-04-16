package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity(name="t_resource")
public class ResourceEntity {

    @Id
    @GenericGenerator(name="rid",strategy = "uuid")
    @GeneratedValue(generator = "rid")
    @Column(name="uid",length = 48)
    private String rid;


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
