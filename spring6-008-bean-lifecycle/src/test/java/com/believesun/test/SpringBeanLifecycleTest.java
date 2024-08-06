package com.believesun.test;

import com.believesun.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifecycleTest {
    @Test
    public void testRegisterBean(){
        User user =  new User();
        System.out.println(user);
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerSingleton("userBean",user);

        // 调用Spring容器中的对象
        User userBean = defaultListableBeanFactory.getBean("userBean", User.class);
        System.out.println(userBean);
    }
    @Test
    public void testBeanLifecycle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        // 第四步：使用Bean对象
        System.out.println("第四步：使用Bean对象 " + user);
        // 销毁对象
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
}
