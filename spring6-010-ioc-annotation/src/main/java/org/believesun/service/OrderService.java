package org.believesun.service;

import org.believesun.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    @Qualifier("orderDaoForOracleImpl")
    private OrderDao orderDao;

    public void generate(){
        orderDao.insert();
    }
}
