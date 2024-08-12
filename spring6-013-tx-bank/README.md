# Spring-jdbcTemplate + Spring的银行转账（事务）

## 注解式声明事务
    第一步：引入XML文件中的事务管理器 DataSourceTransactionManager
```xml
    <!--配置事务管理器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
```
    第二步：引入tx（Teansaction的xml约束）
        xmlns:tx="http://www.springframework.org/schema/tx"
        http://www.springframework.org/schema/tx  http://www.springframework.org/schema/tx/spring-tx.xsd
    
    第三步：开启，事务注解驱动器。
```xml
    <!--开启，注解事务驱动器-->
    <tx:annotation-driven transaction-manager="txManager"/>
```
    第四步：在需要的类、方法上使用@Transactional注解即可。

# 传播行为（Propagation） @Transaction(Propagation = Propagation.REQUIRE)
    什么是传播行为？当 A B 两个事务都被 @Transactional所注释的时候，A 调用 B事务时，事务与事务之间的关系。
    第一种：required（必须的） ---> A 有 B 有，A 无 B 有。
        特点：只要调用不管多少个方法，只会存在一个事务对象！！！
        如果A有事务，则B就用A的事务，如果没有的话，B事务就创建一个事务。
    第二种：support（支持的） ---> A 有 B 有 ，A 无 B 无
        如果A有事务，则B就用A的事务，如果没有，则不创建事务。
    第三种：mandatory（强制的） ---> A 有 B 有，A 无 B 抛异常
        如果A有事务，则B就用A的事务，如果没有，则抛异常。
    第四种：requires_new（必须创建新的）---> A 有/无 B 有
        不管A是否拥有事务，B都创建一个新的事务，且这个新事务与A并列，A事务被挂起
    第五种：not_support(不支持)
        如果A有事务，就挂起A的事务，等B执行完毕。
    第六种：never（从不）
        如果有事务，直接抛异常
    第七种：nested（嵌套）
        如果A有事务，B事务创建自己的事务与其嵌套，各自执行各自的
        如果没事务，则等同于REQUIRE

## 测试REQUIRED传播行为
    A ---> AccountServiceImpl，accountService
    B ---> AccountServiceImpl2，accountService2
    
    A.save(B.save()),A/B @Transactional(propagation = Propagation.REQUIRED) // 有就加入，没有就创建。
