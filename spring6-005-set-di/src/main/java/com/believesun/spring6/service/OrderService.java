package com.believesun.spring6.service;

import com.believesun.spring6.dao.OrderDao;

public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void order(){
        orderDao.createOrder();
    }
}
