package com.believesun.instantiation;

import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean<Person> {

    // 这里的getObject就是我们第三种里面的get方法
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    // 其中，isSingleton为接口实现方法，return true是是单例，false时是多例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
