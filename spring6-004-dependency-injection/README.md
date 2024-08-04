set注入（常用）
当执行了这段语句后
    
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    userDao就被创建出来了

    <!--Service-->
    <bean id="userServiceBean" class="com.believesun.spring6.service.UserService">
        <!--
            为了，让set方法能够被调用，这里需要配置property标签
            这里的name的值的要求：
                将set方法，去掉set，然后把剩下来的首字母变小写2

            public void setUserDao(UserDao userDao) {
                this.userDao = userDao;
            }
            这里
            第一：要确定是哪个set方法
            第二：传入你想注入的bean的id,用ref（references引用）
        -->
        <property name="userDao" ref="userDaoBean"/>
    </bean>

Construct注入（不常用）
第一种：根据下标注入

    <!--根据下标进行注入-->
    <bean id="csBean" class="com.believesun.spring6.service.CustomerService" >
        <!--
            这里使用constructor-agr标签，对构造方法的指定位置的指定属性进行注入操作。
        -->
        <constructor-arg index="0" ref="userDaoBean"/>
        <constructor-arg index="1" ref="vipDaoBean"/>
    </bean>

第二种：根据名字注入

    <!--根据参数名字注入-->
    <bean id="csBean2" class="com.believesun.spring6.service.CustomerService">
        <constructor-arg name="userDao" ref="userDaoBean" />
        <constructor-arg name="vipDao" ref="vipDaoBean" />
    </bean>

第三种：靠spring自己推断（可读性太差不用）
    
    <!--什么都不指定，直接用-->
    <bean id="csBean3" class="com.believesun.spring6.service.CustomerService">
        <constructor-arg ref="userDaoBean" />
        <constructor-arg ref="vipDaoBean" />
    </bean>