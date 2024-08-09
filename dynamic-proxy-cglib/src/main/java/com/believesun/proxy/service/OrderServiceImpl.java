package com.believesun.proxy.service;


public class OrderServiceImpl{
    public void generate() {
        try {
            Thread.sleep(1324);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("订单生成中...");
    }

    public void modify() {
        try {
            Thread.sleep(972);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("订单修改中....");
    }

    public void detail() {
        try {
            Thread.sleep(342);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("正在查看订单信息...");
    }
}
