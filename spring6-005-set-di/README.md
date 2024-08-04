SET注入的专题
1、内部注入
        
    <bean id="orderService2" class="com.believesun.spring6.service.OrderService">
        <!--内部注入-->
        <property name="orderDao">
            <bean class="com.believesun.spring6.dao.OrderDao" />
        </property>
    </bean>

2、外部注入

    <bean id="orderDaoBean" class="com.believesun.spring6.dao.OrderDao" />
    <bean id="orderService" class="com.believesun.spring6.service.OrderService">
        <!--使用外部注入-->
        <property name="orderDao" ref="orderDaoBean" />
    </bean>

3、基本类型的注入
    
    <bean id="orderService3" class="com.believesun.spring6.service.OrderService">
        <property name="变量名" value="值" />
    </bean>

4、哪些是简单类型？
    