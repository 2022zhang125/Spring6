package com.believesun.spring6.service;

import com.believesun.spring6.dao.UserDao;
import com.believesun.spring6.dao.VipDao;

public class CustomerService {
    private UserDao userDao;
    private VipDao vipDao;

    // 构造方法注入
    public CustomerService(UserDao userDao, VipDao vipDao) {
        this.userDao = userDao;
        this.vipDao = vipDao;
    }

    public void saveUser(){
        userDao.insert();
        vipDao.insert();
    }
}
