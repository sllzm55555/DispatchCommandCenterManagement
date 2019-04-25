package com.lovo.service.impl;

import com.lovo.dao.IUserDao;
import com.lovo.entity.UserEntity;
import com.lovo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserEntity login(String username, String password) {

        return userDao.getUserEntityByUsernameAndPassword(username,password);
    }

    @Override
    public UserEntity checkUsernameExist(String username) {

        return userDao.getUserEntityByUsername(username);
    }

    @Override
    public void register(UserEntity userEntity) {
        userDao.save(userEntity);
    }
}
