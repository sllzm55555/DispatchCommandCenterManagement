package com.lovo.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class UserEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 住址
     */
    private String address;

    /**
     * 联系电话
     */
    private String tel;

    @Id
    @GenericGenerator(name = "userUUID" , strategy = "uuid")
    @GeneratedValue(generator = "userUUID")
    @Column(name = "u_id" , length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "u_password" , length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "u_realName" , length = 32)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "u_address" , length = 88)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "u_tel" , length = 11)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
