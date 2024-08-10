package com.believesun.dynamic.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimesInvocationHandler implements InvocationHandler {
    /*
        invoke方法的参数介绍
            Object proxy, Method method, Object[] args
        对于第一个参数：Object proxy
            代表是代理对象，用的比较少。
        对于第二个参数：Method method
            代表是目标对象的目标方法。
        对于第三个参数：Object[] args
            代表的是传递的参数，因为要执行目标方法。
        invoke方法调用时机
            invoke方法，在代理对象调用代理方法时，注册在InvocationHandler对象中的invoke方法自动执行。
     */
    private Object target;

    // 接收目标对象
    public TimesInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 增强代码
        long begin = System.currentTimeMillis();

        // 执行目标对象的目标方法
        // 方法四要素：什么对象，什么方法，什么参数，什么返回值？
        Object retValue = method.invoke(target, args);

        // 这里的retValue是方法的返回值，需要再向上返回，这样才能够被 代理对象的代理方法所接收到。

        long end = System.currentTimeMillis();
        System.out.println("耗费" + (end - begin) + "毫秒");

        return retValue;
    }
}
