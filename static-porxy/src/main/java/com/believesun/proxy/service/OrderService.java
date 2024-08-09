package com.believesun.proxy.service;

public interface OrderService {
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
