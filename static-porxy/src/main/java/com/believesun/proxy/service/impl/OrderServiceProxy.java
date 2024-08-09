package com.believesun.proxy.service.impl;

import com.believesun.proxy.service.OrderService;

public class OrderServiceProxy implements OrderService {
    private OrderService target;

    public OrderServiceProxy(OrderService target) {
        this.target = target;
    }

    @Override
    public void generate() {
        // 代理类增强的代码
        long begin = System.currentTimeMillis();
        // 执行目标对象的方法
        target.generate();
        long end = System.currentTimeMillis();
        System.out.println("耗费"+(end - begin)+"时间");
    }

    @Override
    public void modify() {
        long begin = System.currentTimeMillis();
        // 执行目标对象的方法
        target.modify();
        long end = System.currentTimeMillis();
        System.out.println("耗费"+(end - begin)+"时间");
    }

    @Override
    public void detail() {
        long begin = System.currentTimeMillis();
        // 执行目标对象的方法
        target.detail();
        long end = System.currentTimeMillis();
        System.out.println("耗费"+(end - begin)+"时间");
    }
}
