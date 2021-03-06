package com.lovo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
//设置数据库名字
@Table(name="t_resource")
@Entity
public class ResourceEntity {
    /**
     * 资源id
     * 使用uuid方法  做主键
     * 设置字段名
     */
    @Id
    @GenericGenerator(name="rid",strategy = "uuid")
    @GeneratedValue(generator = "rid")
    @Column(name="resource_id",length = 32)
    private String rid;
    /**
     *资源类型
     */
    @Column(name = "dept_type",length=32)
    private String rtype;
    /**
     *资源名字
     */
    @Column(name = "dept_name",length=24)
    private String rname;
    /**
     *总人数
     */
    @Column(name = "total_polulation")
    private int pnumber;
    /**
     *资源总数
     */
    @Column(name = "total_resources")
    private int cnumber;



    /**
     *URL名字
     */
    @Column(name = "dept_url",length=48)
    private String url;
    /**
     *外键区域id
     * 多对一关系
     */
    @ManyToOne
    @JoinColumn(name = "areaId")
    private AreaEntity  areaEntity;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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

    public AreaEntity getAreaEntity() {
        return areaEntity;
    }

    public void setAreaEntity(AreaEntity areaEntity) {
        this.areaEntity = areaEntity;
    }
}
