# Spring获取Bean的四种方式

## 第一种：直接通过构造方法获取
    SpringBean
```xml
<bean id="sb" class="com.believesun.instantiation.SpringBean" />
```

## 第二种：使用 简单工厂模式 获取
    Stat
    StatFactory 
        -static get() return new Stat();
```xml
    <bean id="stat" class="com.believesun.instantiation.StatFactory" factory-method="get" />
```

## 第三种：使用 工厂方法模式 获取
    Gun
    GunFactory
        - get() return new Gun();
```xml
    <bean id="gunFactory" class="com.believesun.instantiation.GunFactory" />
    <bean id="gun" factory-bean="gunFactory" factory-method="get" />
```

## 第四种：使用FactoryBean接口，来简化第三种方式。
    Person
    PersonFactory implements FactoryBean
        - getObject return new Person();

```xml
    <bean id="person" class="com.believesun.instantiation.PersonFactoryBean"/>
```

## BeanFactory和FactoryBean的区别
    BeanFactory是ApplicationContext的父接口，用来生产Bean对象的
    FactoryBean是一个Bean，是Spring容器实例化对象的一种方式。
        工厂Bean也是一种Bean，只不过这种Bean比较特殊，它可以辅助Spring实例化其它Bean对象。
