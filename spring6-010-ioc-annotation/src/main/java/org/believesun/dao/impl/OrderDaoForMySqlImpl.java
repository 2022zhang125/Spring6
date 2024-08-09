package org.believesun.dao.impl;

import org.believesun.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoForMySqlImpl implements OrderDao {
    @Override
    public void insert() {
        System.out.println("MySql数据库正在插入用户信息...");
    }
}
