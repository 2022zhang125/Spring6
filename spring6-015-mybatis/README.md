# Spring6 继承 Mybatis3.5
    依赖需要：
    <!--Mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.15</version>
    </dependency>
    <!--mybatis-spring-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>3.0.3</version>
    </dependency>
    <!--德鲁伊连接池-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.10</version>
    </dependency>
**注意：这里的mybatis-spring使用的版本必须要是3.0.3不然会报错**

    不需要SqlSessionUtils工具类，不需要mybatis-config.xml文件，将Mybatis核心配置文件继承到spring.xml中
```xml
   <!--SqlSessionFactoryBean-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="typeAliasesPackage" value="com.believesun.bank.pojo"/>
    </bean>

    <!--配置Mapper映射-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.believesun.bank.mapper"/>
    </bean>
```

## 使用Spring.xml的import中导入Dao相关配置文件
    <import resource="dao.xml"/>