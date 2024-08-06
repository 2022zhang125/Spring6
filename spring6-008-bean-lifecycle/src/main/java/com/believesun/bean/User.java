package com.believesun.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class User implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        System.out.println("第二步：给Bean对象赋值（调用Set方法）");
        this.name = name;
    }

    public User() {
        System.out.println("第一步：实例化Bean对象（调用无参数构造方法）");
    }

    public User(String name) {
        this.name = name;
    }

    public void initBean(){
        System.out.println("第三步：初始化Bean对象（调用initBean方法，自己写的，方法名随意。）");
    }

    public void destroyBean(){
        System.out.println("第五步：摧毁Bean对象（调用destroyBean方法，也是自己写的。）");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("第一个点位：该Bean对象的类加载器为：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第一个点位：制造该Bean的Bean工厂为：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("第一个点位：该Bean的名字是：" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第三个点位：摧毁前的操作");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第二个点位：初始化前的操作/before后处理器后的操作");
    }
}
