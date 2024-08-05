# Spring6复习-set注入

## 第一种：注入外部Bean 
```xml
<property name="studentDao" ref="StudentDaoImplBean"/>
```
    Dao层
        StudentDao
            action();
    pojo层
        Student
            name,id
    service层
        StudentServiceImpl
            创建DAO对象，调用其action()方法
     test
        ActionTest
            actionTest()方法调用Service层调用Dao层--->最后打印信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--配置信息，加载Bean对象-->
    <!--DAO层对象-->
    <bean id="StudentDaoImplBean" class="com.believesun.set.dao.StudentDaoImpl"/>
    <!--pojo对象-->
    <bean id="StudentBean" class="com.believesun.set.pojo.Student"/>
    <!--Service对象-->
    <bean id="StudentServiceImplBean" class="com.believesun.set.service.StudentServiceImpl">
        <!--Set注入(外部Bean)-->
        <property name="studentDao" ref="StudentDaoImplBean"/>
    </bean>
</beans>
```
## 第二种：注入内部Bean
```xml
    <!--内部注入Bean-->
    <bean id="StudentServiceImplBean" class="com.believesun.set.service.StudentServiceImpl">
        <property name="studentDao">
            <bean class="com.believesun.set.dao.StudentDaoImpl"/>
        </property>
    </bean>
```

## 第三种：注入简单类型
    什么是简单类型？
        基本数据类型及其包装类，还有Date日期类，不过一般Date日期类都是当做非简单类型来处理的‘
        因为，Date的简单类型注入的value不是 我们所常记的。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dateBean" class="java.util.Date"/>
    <bean id="UserBean" class="com.believesun.set.pojo.User">
        <property name="age" value="12"/>
        <property name="date" ref="dateBean"/>
        <property name="password" value="1212112"/>
        <property name="username" value="zhangsan"/>
    </bean>
</beans>
``` 
## 第四种： 级联属性赋值
    注意：
    ● 顺序不能颠倒。
    ● 在spring配置文件中，clazz属性必须提供getter方法。
```xml
<property name="clazz.name" value="张三" />
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="ClassBean" class="com.believesun.set.pojo.Class" />
    <!--给Student类注入一个Class属性，并且给Class的name进行赋值操作-->
    <bean id="StudentBean" class="com.believesun.set.pojo.Student">
        <property name="clazz" ref="ClassBean" />
        <property name="clazz.name" value="张三" />
        <property name="id" value="1"/>
        <property name="name" value="zhangsi" />
    </bean>
</beans>
```
## 第五种：注入数组和List集合 (使用list标签)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--注入数组-->
    <bean id="PersonBean" class="com.believesun.set.pojo.Person">
        <property name="favoriteFoods">
            <array>
                <value>土豆</value>
                <value>番茄</value>
                <value>西红柿</value>
            </array>
        </property>
        <!--注入List集合-->
        <property name="hobbit">
            <list>
                <value>抽烟</value>
                <value>喝酒</value>
                <value>烫头</value>
            </list>
        </property>
    </bean>
</beans>
```
## 第六种：注入Map集合（使用map标签）
    注意：
    ● 如果key是简单类型，使用 key 属性，反之使用 key-ref 属性。
    ● 如果value是简单类型，使用 value 属性，反之使用 value-ref 属性。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--注入Map数组-->
    <bean id="PeopleBean" class="com.believesun.set.pojo.People">
        <property name="addr">
            <map>
                <entry key="1" value="安徽省六安市"/>
                <entry key="2" value="湖北省黄石市"/>
            </map>
        </property>
    </bean>
</beans>
```
## 第七种：注入properties
    Properties继承了HashMap接口，所以本质上还是一个Map集合
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="PeopleBean" class="com.believesun.set.pojo.People">
        <!--注入Map数组-->
        <property name="addr">
            <map>
                <entry key="1" value="安徽省六安市"/>
                <entry key="2" value="湖北省黄石市"/>
            </map>
        </property>
        <!--注入Properties集合-->
        <property name="properties">
            <props>
                <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
                <prop key="username">root</prop>
                <prop key="password">111111</prop>
                <prop key="url">jdbc://mysql:localhost:3306/spring6</prop>
            </props>
        </property>
    </bean>
</beans>
```

## 第八种：注入Null和空字符串
    注入空字符串使用：<value/> 或者 value=""
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="VipBean" class="com.believesun.set.pojo.Vip">
        <!--注入空字符串-->
        <property name="email" value=""/>
    </bean>
</beans>
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="VipBean" class="com.believesun.set.pojo.Vip">
        <!--注入空字符串-->
        <property name="email">
            <value/>
        </property>
    </bean>
</beans>
```
    注入null使用：<null/> 或者 不为该属性赋值
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="VipBean" class="com.believesun.set.pojo.Vip">
        <property name="email">
            <null/>
        </property>
    </bean>
</beans>

```
## 第九种：注入的值中含有特殊字符（> < 引号等）
    ● 第一种：特殊符号使用转义字符代替。
        >	&gt;
        <	&lt;
        '	&apos;
        "	&quot;
        &	&amp;
    ● 第二种：将含有特殊符号的字符串放到：<![CDATA[]]> 当中。因为放在CDATA区中的数据不会被XML文件解析器解析。
```xml
<property name="result">
            <!--只能使用value标签-->
            <value><![CDATA[2 < 3]]></value>
</property>
```

# P命名空间注入
    第一行加入：xmlns:p="http://www.springframework.org/schema/p"
    引入P命名空间注入，C命名空间注入，以及util命名空间注入以简化赋值操作。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--P命名空间注入-->
    <!--
        第一步：复制xmlns="http://www.springframework.org/schema/beans"
        第二步：添加p
        第三步：xmlns:p="http://www.springframework.org/schema/p"
    -->
    <!--加载Bean-->
    <bean id="CustomerBean" class="com.believesun.set.pojo.Customer" p:age="20" p:name="张三" />
</beans>
```

# C命名空间注入(基于构造方法的注入)
    添加:xmlns:c="http://www.springframework.org/schema/c"
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="CustomerBean" class="com.believesun.set.pojo.Customer" c:_0="李四" c:_1="30"/>
</beans>

```

# Util命名空间注入
    在第一行加入：
       xmlns:util="http://www.springframework.org/schema/util"
    在最后一行加入：
       http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
    <util:properties id="prop">
        <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
        <prop key="url">jdbc:mysql://localhost:3306/spring6</prop>
        <prop key="username">root</prop>
        <prop key="password">111111</prop>
    </util:properties>
    <!--创建Bean对象，类似于Mybatis的结果映射 （resultMap）-->
    <bean id="MyDataSource01Bean" class="com.believesun.set.pojo.MyDataSource01">
        <property name="properties" ref="prop" />
    </bean>
</beans>
```
# byName通过名称自动注入
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="userService" class="com.powernode.spring6.service.UserService" autowire="byName"/>
    <bean id="aaa" class="com.powernode.spring6.dao.UserDao"/>
</beans>
```
    这里使用autowire="byName" 自动装配，将其set方法的setAAA，直接装配到userService中。

# byType依据类型注入（构造方法）autowire="byType"
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="accountService" class="com.powernode.spring6.service.AccountService" autowire="byType"/>
    <bean id="x" class="com.powernode.spring6.dao.AccountDao"/>
    <bean id="y" class="com.powernode.spring6.dao.AccountDao"/>
</beans>
```

# Spring引入外部Properties文件（jdbc.properties）
    引入方式类似于util命名空间
    在导入外部props文件时，需要先
    <context:property-placeholder location="jdbc.properties" />
    然后使用${}来取值，这里使用 jdbc. 的方式是为了避免其中的username取到系统的用户名，所以使用jdbc去约束
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <!--引入外部properties文件，这里需要用到Context命名空间-->
    <context:property-placeholder location="jdbc.properties" />

    <bean id="MyDataSourceBean" class="com.believesun.set.pojo.MyDataSource">
        <property name="driver" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
    </bean>
</beans>
```
