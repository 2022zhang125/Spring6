package com.believesun.spring6.test;

import com.believesun.spring6.dao.UserDao;
import com.believesun.spring6.service.CustomerService;
import com.believesun.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    @Test
    public void testConstructDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        CustomerService customerService = applicationContext.getBean("csBean", CustomerService.class);
        customerService.saveUser();
    }
    @Test
    public void testSetDI(){
        // 创建UserService对象
        // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // System.out.println("applicationContext创建了！！！！");
        UserService userServiceBean = applicationContext.getBean("userServiceBean", UserService.class);
        // System.out.println("userServiceBean创建了！！！！");
        // UserDao userDao1 = userServiceBean.getUserDao();
        // System.out.println(userDao1);

        userServiceBean.saveUser();

        // UserDao userDao2 = userServiceBean.getUserDao();
        // System.out.println(userDao2);
    }
}
