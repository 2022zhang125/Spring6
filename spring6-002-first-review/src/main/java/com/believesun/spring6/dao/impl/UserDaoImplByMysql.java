package com.believesun.spring6.dao.impl;

import com.believesun.spring6.dao.UserDao;

public class UserDaoImplByMysql implements UserDao {

    @Override
    public void UserDelete() {
        System.out.println("用户删除成功");
    }
}
