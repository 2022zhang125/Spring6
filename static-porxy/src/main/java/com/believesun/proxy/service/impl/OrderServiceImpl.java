package com.believesun.proxy.service.impl;

import com.believesun.proxy.service.OrderService;

public class OrderServiceImpl implements OrderService {
    @Override
    public void generate() {
        try {
            Thread.sleep(1324);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("订单生成中...");
    }

    @Override
    public void modify() {
        try {
            Thread.sleep(972);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("订单修改中....");
    }

    @Override
    public void detail() {
        try {
            Thread.sleep(342);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("正在查看订单信息...");
    }
}
