package com.believesun.proxy.service;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TimerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 增强
        long begin = System.currentTimeMillis();
        // args -> 传参，proxy -> 代理对象方法 通过子类，调用super父类方法即可
        Object retValue = proxy.invokeSuper(obj, args);
        long end = System.currentTimeMillis();
        System.out.println("耗时为：" + (end - begin));
        return retValue;
    }
}
