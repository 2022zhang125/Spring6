package com.believesun.bean.test;

import com.believesun.bean.Husband;
import com.believesun.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CircularDependencyTest {
    @Test
    public void testCD2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring2.xml");
        Husband h = applicationContext.getBean("h", Husband.class);
        System.out.println(h);

        Husband w = applicationContext.getBean("w", Husband.class);
        System.out.println(w);
    }
    @Test
    public void testCD(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Husband husbandBean = applicationContext.getBean("husbandBean", Husband.class);
        System.out.println(husbandBean);

        Wife wifeBean = applicationContext.getBean("wifeBean", Wife.class);
        System.out.println(wifeBean);
    }
}
