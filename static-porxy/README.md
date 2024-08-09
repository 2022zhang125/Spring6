# 静态代理
    角色：
        目标对象：OrderServiceImpl
        代理对象：OrderServiceProxy
            private OrderService target;
        目标对象和代理对象的公共接口：OrderService
    客户端：
```java
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
```

## 需求：计算每个方法所用时间
    第一种：直接修改OrderServiceImpl的代码加入时间差
        缺点1：违背了OCP开闭原则
        缺点2：代码复用低

    第二种：使用泛化关系（继承关系）
        符合了OCP的开闭原则，但是耦合度过高
        ps:继承关系是一种耦合度很高的关系，不推荐使用。

    第三种：代理模式（静态代理模式）
        用OrderServiceProxy类去实现公共接口OrderService，然后添加其增强的代码。
        也就是用 关联关系 has a 去代替 泛化关系 is a
        缺点1：如果有1000个类实现了OrderService类，就需要写1000个对应的proxy代理类
        会导致类爆炸问题。
        缺点2：任然是没有解决代码复用问题

    怎么解决类爆炸问题？
        使用动态代理机制，在内存中动态的创建字节码代理类，使用"字节码生成技术"去避免类爆炸问题。