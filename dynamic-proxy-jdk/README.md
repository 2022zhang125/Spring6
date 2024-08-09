# 动态代理-JDK
    使用JDK自带的Proxy动态代理方式
    java.lang.reflect.Proxy
```java
package com.believesun.dynamic.client;

import com.believesun.dynamic.service.OrderService;
import com.believesun.dynamic.service.impl.OrderServiceImpl;
import com.believesun.dynamic.service.impl.TimesInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * 客户端程序
 */
public class Client {
    public static void main(String[] args) {
        // 1.创建目标对象
        OrderService target = new OrderServiceImpl();
        // 2.创建代理对象
        OrderService proxy = (OrderService)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimesInvocationHandler(target));
        // 3.调用代理对象的代理方法
        proxy.generate();
        proxy.modify();
        proxy.detail();
        String name = proxy.getName("李四");
        System.out.println(name);
    }
}

```