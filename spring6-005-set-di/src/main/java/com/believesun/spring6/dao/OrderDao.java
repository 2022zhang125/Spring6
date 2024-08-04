package com.believesun.spring6.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Date;

public class OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderDao.class);
    public void createOrder(){
        logger.info("订单已创建！！！！");
    }
}
