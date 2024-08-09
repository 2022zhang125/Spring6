package com.believesun.proxy.bean;

import com.believesun.proxy.service.OrderService;
import com.believesun.proxy.service.impl.OrderServiceImpl;
import com.believesun.proxy.service.impl.OrderServiceProxy;

public class Test {
    public static void main(String[] args) {
        // 创建目标对象
        OrderService target = new OrderServiceImpl();
        // 创建代理对象
        OrderService proxy = new OrderServiceProxy(target);
        // 调用代理对象的方法
        proxy.detail();
        proxy.modify();
        proxy.generate();
    }
}
