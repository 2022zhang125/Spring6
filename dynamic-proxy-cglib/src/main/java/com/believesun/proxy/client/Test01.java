package com.believesun.proxy.client;

import com.believesun.proxy.service.OrderServiceImpl;
import com.believesun.proxy.service.TimerMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;

public class Test01 {
    public static void main(String[] args) {
        // 创建字节码增强器,这里使用Spring自带的就行
        Enhancer enhancer = new Enhancer();

        // 由于底层是继承的，所以这里我们需要告诉cglib去继承谁
        enhancer.setSuperclass(OrderServiceImpl.class);

        // 设置回调接口 == InvocationHandler
        enhancer.setCallback(new TimerMethodInterceptor());

        // 创建代理对象
        OrderServiceImpl orderService = (OrderServiceImpl) enhancer.create();

        // 调用代理对象的代理方法
        orderService.generate();
        orderService.modify();
        orderService.detail();
    }
}
