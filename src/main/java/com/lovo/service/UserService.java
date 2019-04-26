package com.lovo.service;

import com.lovo.entity.UserEntity;

public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserEntity login(String username,String password);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserEntity checkUsernameExist(String username);

    /**
     * 注册
     * @param userEntity
     */
    void register(UserEntity userEntity);
}
