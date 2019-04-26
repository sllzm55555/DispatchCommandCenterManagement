package com.lovo.dao;

import com.lovo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UserEntity,String> {

    /**
     * 登录系统
     * @param username
     * @param password
     * @return
     */
    UserEntity getUserEntityByUsernameAndPassword(String username,String password);

    /**
     * 根据用户名查找用户对象
     * @param username
     * @return
     */
    UserEntity getUserEntityByUsername(String username);

}
