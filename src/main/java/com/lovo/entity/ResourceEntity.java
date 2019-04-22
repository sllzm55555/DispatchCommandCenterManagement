package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name="t_resource")
@Entity
public class ResourceEntity {

    @Id
    @GenericGenerator(name="rid",strategy = "uuid")
    @GeneratedValue(generator = "rid")
    @Column(name="resource_id",length = 48)
    private String rid;
    @Column(name = "dept_area",length=48)
    private String  area;
    @Column(name = "dept_type",length=48)
    private String rtype;
    @Column(name = "dept_name",length=48)
    private String rname;
    @Column(name = "total_polulation")
    private int pnumber;
    @Column(name = "total_resources")
    private int cnumber;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public int getPnumber() {
        return pnumber;
    }

    public void setPnumber(int pnumber) {
        this.pnumber = pnumber;
    }

    public int getCnumber() {
        return cnumber;
    }

    public void setCnumber(int cnumber) {
        this.cnumber = cnumber;
    }
}
