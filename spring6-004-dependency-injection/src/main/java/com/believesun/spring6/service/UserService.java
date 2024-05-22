package com.believesun.spring6.service;

import com.believesun.spring6.dao.UserDao;

public class UserService {
    private UserDao userDao;
    // 使用Set注入的方式对userDao对象进行赋值

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        System.out.println("userDao创建了！！！！！");
        this.userDao = userDao;
    }

    public void saveUser(){
        userDao.insert();
    }
}
