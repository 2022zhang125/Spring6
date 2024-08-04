package com.believesun.spring6.test;

import com.believesun.spring6.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderServiceSetDITest {
    @Test
    public void testOrder(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("set-di.xml");
        OrderService orderService = applicationContext.getBean("orderService2", OrderService.class);
        orderService.order();
    }
}
