package com.believesun.spring6.test;

import com.believesun.spring6.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {
    @Test
    public void testFirstSpringCode(){
        // 创建Spring容器
        // 当执行完这行代码时，Spring会解析XMl文件，将所有的Bean都创建出来，然后放到Spring容器中。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // 从容器中获取对应ID的对象
        User userBean = (User) applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);
    }
}
