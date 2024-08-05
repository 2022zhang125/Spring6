# Bean的作用域
    scope的值有很多（八个）,一般默认情况下是 单例的（singleton），可以手动设置成 多例的、原型（prototype）
    还有 request session 这些事web项目中特有的，需要在Maven中使用SpringMVC
    其他的还有 global session, application, websocket，自定义范围

## singleton和prototype（原型）的区别
```xml
    <bean id="sb" class="com.believesun.scope.bean.SpringBean" scope="prototype"/>
```
    signleton是单例的，在new ClassPathXmlApplicationContext()的时候，创建对象，调用getBean时，只是调用AOP容器中的对象，没有创建
    prototype是多例的，在new 容器的时候不会创建对象，而是在getBean的时候创建对象。
    因此singleton是不支持线程安全的，在不同的线程中都是一个对象。这里我们引入了自定义scope范围，让一个线程一个Bean对象。

## 如何自定义scope范围？
    这里需求是，一个线程一个bean
    我们需要去创建一个类，去实现scope接口，然后将该类注册到AOP容器中，最后在scope=中使用。
    这里的类Spring已经创建好了：SimpleThreadScope（简单线程范围），但是我们需要配置一下在：CustomScopeConfigurer（自定义 范围 配置器）中配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--自定义scope-->
    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
        <property name="scopes">
            <map>
                <entry key="myThread">
                    <bean class="org.springframework.context.support.SimpleThreadScope" />
                </entry>
            </map>
        </property>
    </bean>
    <bean id="sb" class="com.believesun.scope.bean.SpringBean" scope="myThread"/>

</beans>
```
        public void testCustomScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBeanScope.xml");
        SpringBean sb1 = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb1);
        SpringBean sb2 = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb2);

        // 分支线程
        new Thread(() -> {
            SpringBean sb3 = applicationContext.getBean("sb", SpringBean.class);
            System.out.println(sb3);
            SpringBean sb4 = applicationContext.getBean("sb", SpringBean.class);
            System.out.println(sb4);
        }).start();
    }
    
    运行结果
    G:\Java\jdk-19.0.2\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:G:\IDEA\IntelliJ IDEA 2022.2.2\lib\idea_rt.jar=10234:G:\IDEA\IntelliJ IDEA 2022.2.2\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "G:\IDEA\IntelliJ IDEA 2022.2.2\lib\idea_rt.jar;G:\IDEA\IntelliJ IDEA 2022.2.2\plugins\junit\lib\junit5-rt.jar;G:\IDEA\IntelliJ IDEA 2022.2.2\plugins\junit\lib\junit-rt.jar;G:\spring6\spring6-006-scope\target\test-classes;G:\spring6\spring6-006-scope\target\classes;G:\Maven\maven-repo\junit\junit\4.13.2\junit-4.13.2.jar;G:\Maven\maven-repo\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;G:\Maven\maven-repo\org\springframework\spring-context\6.1.6\spring-context-6.1.6.jar;G:\Maven\maven-repo\org\springframework\spring-aop\6.1.6\spring-aop-6.1.6.jar;G:\Maven\maven-repo\org\springframework\spring-beans\6.1.6\spring-beans-6.1.6.jar;G:\Maven\maven-repo\org\springframework\spring-core\6.1.6\spring-core-6.1.6.jar;G:\Maven\maven-repo\org\springframework\spring-jcl\6.1.6\spring-jcl-6.1.6.jar;G:\Maven\maven-repo\org\springframework\spring-expression\6.1.6\spring-expression-6.1.6.jar;G:\Maven\maven-repo\io\micrometer\micrometer-observation\1.12.5\micrometer-observation-1.12.5.jar;G:\Maven\maven-repo\io\micrometer\micrometer-commons\1.12.5\micrometer-commons-1.12.5.jar;G:\Maven\maven-repo\org\apache\logging\log4j\log4j-core\2.19.0\log4j-core-2.19.0.jar;G:\Maven\maven-repo\org\apache\logging\log4j\log4j-api\2.19.0\log4j-api-2.19.0.jar;G:\Maven\maven-repo\org\apache\logging\log4j\log4j-slf4j2-impl\2.19.0\log4j-slf4j2-impl-2.19.0.jar;G:\Maven\maven-repo\org\slf4j\slf4j-api\2.0.0\slf4j-api-2.0.0.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 com.believesun.scope.test.SpringBeanScopeTest,testCustomScope
    DEBUG  |2024-08-05 19:29:52|[main]|org.springframework.context.support.AbstractApplicationContext.prepareRefresh(AbstractApplicationContext.java:671)|Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@47c81abf
    DEBUG  |2024-08-05 19:29:52|[main]|org.springframework.beans.factory.xml.XmlBeanDefinitionReader.doLoadBeanDefinitions(XmlBeanDefinitionReader.java:402)|Loaded 2 bean definitions from class path resource [SpringBeanScope.xml]
    DEBUG  |2024-08-05 19:29:52|[main]|org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:225)|Creating shared instance of singleton bean 'org.springframework.beans.factory.config.CustomScopeConfigurer#0'
    构造方法执行了...
    com.believesun.scope.bean.SpringBean@19553973
    com.believesun.scope.bean.SpringBean@19553973
    构造方法执行了...
    com.believesun.scope.bean.SpringBean@1fc175fd
    com.believesun.scope.bean.SpringBean@1fc175fd
    
    Process finished with exit code 0