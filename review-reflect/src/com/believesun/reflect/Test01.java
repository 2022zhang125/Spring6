package com.believesun.reflect;

import java.lang.reflect.Method;

public class Test01 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.believesun.reflect.doSomeService");
        Method declaredMethod = clazz.getDeclaredMethod("doSome",String.class,int.class);
        Object obj = declaredMethod.invoke(clazz.newInstance(), "张三", 123);
        System.out.println(obj);
    }
}
