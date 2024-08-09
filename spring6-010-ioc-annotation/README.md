# Spring框架中声明Bean的注解
    第一个：@Component注解
    第二个：@Controller
    第三个：@Service
    第四个：@Repository

## 区别
    结论：四个注解一摸一样，除了第一个以外，其余三个都是为了增强代码可读性所写的。
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Indexed
    public @interface Component {
        String value() default "";
    }

## 使用
    第一步：确认spring-context包中含有 spring-aop 包

    第二步：在配置文件中引入context，然后定义基本路径,如果有不同的包的话，可以加 逗号
    <context:component-scan base-package="com.believesun.spirng6.bean,com.believesun.spring6.dao" />

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <!--注册context-->
    <!--用context定义基本bean包路径-->
    <context:component-scan base-package="com.believesun.spirng6.bean" />
</beans>
```

    第三步：使用注解

```java
package com.believesun.spirng6.bean;

import org.springframework.stereotype.Component;

@Component
public class User {

}
```
    在类上加入注解，然后给定BeanID，如果没有的话，Spring会自动获取其类名 Aaaa -> aaaa 变为其小写为BeanId
    第一种：
        @Component({value = "userBean"})
    第二种：（由于，只有一个value属性名，所以可以舍去 {} ）
        @Component(value = "userBean")
    第三种：（由于，当属性为value时，也可以舍去 value）
        @Component("userBean")
    第四种：由Spring自己定义的。
        @Component

## 如何选择性的声明Bean对象？
    第一种，让所有的的Bean对象的声明失效，然后着重让想声明的对象进行声明。(exclude-filter)
```xml
    <context:component-scan base-package="com.believesun.spirng6.bean" use-default-filters="true">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Component"/>
    </context:component-scan>
```

    第二种，让所有的Bean加载，然后排出不需要的Bean对象。(inclued-filter)
```xml
    <context:component-scan base-package="com.believesun.spirng6.bean" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Component"/>
    </context:component-scan>
```

# 通过注解进行注入简单类型操作（@Value）

## 使用
    @Value注解不仅可以使用在对应简单类型的属性上，如果简单类型有其Setter方法的话，可以注解在Setter方法上。更nb的是
    可以注解在 构造方法上

### 第一种：注解在简单类型的属性上。
```java
package com.believesun.spirng6.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解给简单类型进行赋值操作
 */
@Component
public class Student {
    @Value("张三")
    private String name;
    @Value("23")
    private int age;
    @Value("1010110")
    private String id;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
```
### 第二种：注解在Setter方法上。
```java
package com.believesun.spirng6.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解给简单类型进行赋值操作
 */
@Component
public class Student {
    private String name;
    private int age;
    private String id;

    @Value("李四")
    public void setName(String name) {
        this.name = name;
    }
    @Value("40")
    public void setAge(int age) {
        this.age = age;
    }

    @Value("1231231")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
```

### 第三种：注解在构造方法上
```java
package com.believesun.spirng6.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解给简单类型进行赋值操作
 */
@Component
public class Student {
    private String name;
    private int age;
    private String id;
    public Student(@Value("王五") String name, @Value("98") int age, @Value("9282933") String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
```

# 通过注解，注入 非简单类型 （@Autowired,@Qualifier）
    
## 对于 @Autowired注解
    默认使用 byType 自动装配，什么是通过类型自动装配呢？
        如果 private OrderDao orderDao中注入orderDao元素
        通过该注解注入时，需确保只有一个实现类实现该接口，这样才能够通过自动类型装配的方式注入元素
### 出现的位置
    第一个：属性上
        @Autowired
        private OrderDao orderDao
    第二个：Setter方法上
        @Autowired
        public OrderDao setOrder(OrderDao orderDao){
            this.orderDao = orderDao;
        }
    第三个：构造方法上
        @Autowired
        public OrderService(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
    第四种：构造方法属性上
        public OrderService(@Autowired OrderDao orderDao) {
            this.orderDao = orderDao;
        }
    第五种：如果只有一个构造方法(有无参数都不行)，且构造方法的方法名和定义的属性名一致的情况下，@Autowired注解可以省略（最好不省去）
        private OrderDao orderDao;
        public OrderService(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
```java
package org.believesun.service;

import org.believesun.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public void generate(){
        orderDao.insert();
    }
}
```

## 如果不止一个实现类的话，也就是说，不能通过类型自动装配的情况下，我们需要通过 名字自动装配。@Qualifier
```java
package org.believesun.service;

import org.believesun.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    @Qualifier("orderDaoForOracleImpl")
    private OrderDao orderDao;

    public void generate(){
        orderDao.insert();
    }
}
```
    通过@Qualifier来通过名字注入特定的对象。

# 注入非简单类型的第二种方式 @Resource 注解（推荐）
    这个注解是jdk扩展包的，不是Spring框架自带的，JDK扩展包的肯定支持的范围要广一些
    在Spring6及其以上，使用的是jakarta的包，Spring5及其以下，用的是javax的包。

## 出现的位置
    第一个：属性上
    第二个：setter方法上

## 使用
    @Resource注解有一个name属性，其注入的方式如下
        首先，@Resource会通过 name 的形式进行自动注入
        如果name没有定义的话，则会通过 属性名 作为name再次进行自动注入
        如果属性名作为 name 还是找不到的话，这时才会考虑到使用 byType 通过类型注入

    @Resource("name = orderDaoForOracle")
    private OrderDao orderDao;

    @Resource
    private OrderDao orderDao;
    他就会去找 @Component()叫 orderDao 的Bean对象。
    如果还是找不着的话，就会根据类型进行装配了。

# 全注解开发，不使用任何的XML文件
    第一步：定义一个类来代替配置文件
        在该类上加入@Configuration和@ComponentScan({"org.believesun.dao","org.believesun.service"})注解

    第二步：获取Bean对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring6Config.class);
        context.getBean("orderBean",OrderBean.class).generate();

