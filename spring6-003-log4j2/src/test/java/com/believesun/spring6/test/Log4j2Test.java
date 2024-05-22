package com.believesun.spring6.test;

import com.believesun.spring6.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Log4j2Test {
    @Test
    public void userTest(){
        // 创建对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("userSpring.xml");
        User user = applicationContext.getBean("user", User.class);
        user.setId(12);
        user.setName("张三");
        user.setSex('男');
        System.out.println(user);
    }
}
