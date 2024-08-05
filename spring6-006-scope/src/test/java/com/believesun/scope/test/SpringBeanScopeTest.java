package com.believesun.scope.test;

import com.believesun.scope.bean.SpringBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class SpringBeanScopeTest {
    @Test
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

    @Test
    public void testSingletonOrPrototype() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringBeanScope.xml");
        SpringBean sb = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb);

        SpringBean sb2 = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb2);

        SpringBean sb3 = applicationContext.getBean("sb", SpringBean.class);
        System.out.println(sb3);
    }
}
