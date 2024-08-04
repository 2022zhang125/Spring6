package com.believesun.spring6.service.impl;

import com.believesun.spring6.dao.UserDao;
import com.believesun.spring6.service.UserService;

public class UserServiceImpl implements UserService {
    // 引入DIP原则
    // private UserDao userDao = new UserDaoImplByMysql();
    private UserDao userDao;
    @Override
    public void UserServiceDelete() {
        userDao.UserDelete();
    }
}
