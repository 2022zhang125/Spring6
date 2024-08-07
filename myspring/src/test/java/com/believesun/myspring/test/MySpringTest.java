package com.believesun.myspring.test;

import com.believesun.bean.UserService;
import org.junit.Test;
import org.myspringfarmework.core.ApplicationContext;
import org.myspringfarmework.core.ClassPathXmlApplicationContext;

public class MySpringTest {
    @Test
    public void testDom4j(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myspring.xml");
        Object user = applicationContext.getBean("user");
        Object userService = applicationContext.getBean("userService");
        UserService service = (UserService) userService;
        service.save();
        System.out.println(user);
    }
}
