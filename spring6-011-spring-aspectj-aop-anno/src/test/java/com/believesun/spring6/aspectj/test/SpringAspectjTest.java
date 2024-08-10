package com.believesun.spring6.aspectj.test;

import com.believesun.spring6.SpringConfig;
import com.believesun.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAspectjTest {
    @Test
    public void testBefore() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.login();
    }
}
