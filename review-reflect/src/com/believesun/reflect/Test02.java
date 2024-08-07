package com.believesun.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 已知
 * 类名：com.believesun.reflect.User
 * 类符合JavaBean规范
 * 类中有属性 int age
 * 需求：
 * 通过反射机制调用setter方法给age赋值
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        String className = "com.believesun.reflect.User";
        String paramName = "age";
        int inputVal = 12;

        Object obj = setParams(className, paramName, inputVal);
        System.out.println(obj);
    }

    public static Object setParams(String className, String paramName, int inputVal) {
        try {
            Class<?> targetClazz = Class.forName(className);
            // 通过属性名 ---> 获取属性类型
            Field field = targetClazz.getDeclaredField(paramName);
            Class<?> paramType = field.getType();

            Method setMethod = targetClazz.getDeclaredMethod("set" + paramName.toUpperCase().charAt(0) + paramName.substring(1), paramType);
            Object obj = targetClazz.getDeclaredConstructor().newInstance();
            setMethod.invoke(obj, inputVal);
            return obj;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
