package org.believesun.dao.impl;

import org.believesun.dao.OrderDao;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoForOracleImpl implements OrderDao {
    @Override
    public void insert() {
        System.out.println("Oracle数据库正在保存用户信息...");
    }
}
