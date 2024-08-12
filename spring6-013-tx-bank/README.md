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

    如果A,B都使用REQUIRED的话，其中A会创建事务对象，而B会直接采用A的事务对象，使两个save中使用一个事务对象.
    Creating new transaction with name [com.believesun.bank.service.impl.AccountServiceImpl.save]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
    Participating in existing transaction
    如果B出现异常的话，A和B同时回滚。
    
    如果A使用REQUIRED，而B采用REQUIRES_NEW 的方式的话，A与B会并列执行
    如果B中出现异常在A中捕捉后，可继续执行A中代码，而B回滚
    如果A不捕捉，则A，B同时回滚

# 隔离级别（Isolation） Oracle二挡起步，MySql三挡起步（隔离级别）
    注意：只有读的时候，才涉及到三大读问题，才需要设置隔离级别
    DEFAULT(-1),                |
    读未提交：READ_UNCOMMITTED(1),        |   自上而下隔离级别 三大问题都存在 
    读提交：READ_COMMITTED(2), Oracle   |   越来越高。 存在不可重复读和幻读问题
    可重复读：REPEATABLE_READ(4), Mysql(RR级别，重复读) 只存在幻读问题
    序列化：SERIALIZABLE(8);效率最低，但是不存在三大读问题                

## 三大读问题
    脏读：读取到内存中的数据了
    不可重复读：每次读取的数据不同，但是数据是对的
    幻读：数据与脑子中的数据对不上

## 测试READ_UNCOMMITTED和READ_COMMITTED
    读未提交：对方还没有进行提交的时候，我就能读到数据了
        A读取B正在插入的事务user A.Isolation = READ_UNCOMMITTED
        如果A在读取的时候，B还没有进行提交操作
        那么，就会出现脏读的情况！！！
    读提交：对方不提交，我就读不到数据（在JdbcTemplate中，读取不到数据时会报异常。）  

# 事务超时（timeout）
    当一个事务中的所有DML语句执行完成的时间超过了timeout的时间时，会选择回滚！！！
        注意：是所有的DML语句
    如下：这种就会导致回滚，因为所有的DML还没有执行完毕。
        void save(){
            Sleep(1000*20)
            accountDao.insert();
        }
    如下：这种就不会导致回滚，因为Sleep语句后面不存在DML语句了。
        void save(){
            accountDao.insert();
            Sleep(1000*20)
        }
**总结：超时时间 = 最后一条DML语句执行完毕的时间！**

# 只读事务（readOnly = true）
    这个事务不能执行DML语句。启用Spring的优化策略，提高select语句的执行效率。

# 异常回滚事务（rollbackFor = RuntimeException.class）
    出现RuntimeException或其子类的时候，才进行回滚事务。别的异常就不回滚了（正常提交）。

# 异常不回滚事务（noRollbackFor = NullPointException）
    当异常时NPE的时候就不回滚（正常提交）。其他异常就回滚

# 使用全注解代替XML文件