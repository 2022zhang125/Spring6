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
    注入null使用：<null/> 或者 不为该属性赋值
