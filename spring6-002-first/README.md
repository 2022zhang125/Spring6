1.导入依赖
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    <!--如果想要使用JDBC，tx还需要再次添加依赖-->
    <!--Spring Context依赖,Spring的基础依赖-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.6</version>
    </dependency>

2.创建Spring配置文件

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    </beans>

文件名可自定义，叫啥都行。

3.在配置文件中配置我们需要操作的bean类。
    <bean id="" class="" />
    id:就是Bean的身份证，不能重复的！！！
    class：全限定名称，带包名的那种。

4.测试第一个Spring项目

    @Test
    public void testFirstSpringCode(){
        // 创建Spring容器
        // 当执行完这行代码时，Spring会解析XMl文件，将所有的Bean都创建出来，然后放到Spring容器中。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 从容器中获取对应ID的对象
        User userBean = (User) applicationContext.getBean("userBean");
        System.out.println(userBean);
    }
注意：
    1.bean标签中的id决定不能重复！！！
    2.Spring底层是如何创建对象的呢？
        通过反射机制，调用bean的无参数构造方法创建的。
        Clazz clazz = Class.forName("com.believesun.spring6.dao.UserDaoImplForMysql");
        Object obj = clazz.newInstance();
    3.必须要含有无参构造方法，在有参的时候也要写一个。
    4.底层存储方式
        key(id)     value(class)
    --------------------------------
        userBean    com.believesun.spring6.dao.UserDaoImplForMysql
        .....
    5.Spring的配置文件可以有多个。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(Spring XMl Name01,Spring XMl Name02,xml/spring.xml);
    6.也可以是非自定义的类。
    7.如果getBean后id不存在，则报错，不会返回null
    8.getBean指定返回参数，ApplicationContext.getBean("id",你需要指定返回类型的Class)
    9.如果Spring的配置文件不在resources下，用FileSystemXmlApplicationContext("绝对路径");去获取

ApplicationContext的超级父接口是BeanFactory（Bean工厂）
    BeanFactory是顶级接口。使用了工厂模式。
不是在调用getBean方法的时候实例化对象，当我们运行了 new ClassPathXmlApplicationContext()的时候，就实例化好了！！！