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

        // newProxyInstance可以在内存中创建字节码文件，然后加载成代理对象
        /**
         * public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h);
         * 第一个参数：ClassLoader loader 类加载器
         *      JDK规定，代理类的类加载器，需要与目标对象的类加载器必须一致。
         *
         * 第二个参数：Class<?>[] interfaces 公共接口
         *      这个接口必须要与目标对象的接口一致，可能是一些也可能是一个。
         *
         * 第三个参数：InvocationHandler h 调用处理器
         *      通过这个调用处理器来 加入一些增强功能的代码，以及执行目标对象的目标方法。
         *      由于我们在其调用处理器中需要 目标类的目标方法，所以这里我们需要将目标对象传入 调用处理器中。
         */
        /*Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimesInvocationHandler(target));*/
        // 向下转型，因为与目标对象实现了相同的接口，所以可以直接强转为接口对象
        OrderService proxy = (OrderService)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TimesInvocationHandler(target));
        // 3.调用代理对象的代理方法
        proxy.generate();
        proxy.modify();
        proxy.detail();
        String name = proxy.getName("李四");
        System.out.println(name);
    }
}
