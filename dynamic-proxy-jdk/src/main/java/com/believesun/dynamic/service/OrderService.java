package com.believesun.dynamic.service;

public interface OrderService {
    /**
     * 添加返回值的方法
     */
    String getName(String s);
    /**
     * 生成订单
     */
    void generate();

    /**
     * 修改订单
     */
    void modify();

    /**
     * 查看订单信息
     */
    void detail();
}
