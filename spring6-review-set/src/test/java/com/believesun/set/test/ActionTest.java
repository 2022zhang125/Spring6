package com.believesun.set.test;

import com.believesun.set.pojo.*;
import com.believesun.set.service.StudentServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActionTest {
    @Test
    public void testOutPropertiesDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-springDI.xml");
        MyDataSource myDataSourceBean = applicationContext.getBean("MyDataSourceBean", MyDataSource.class);
        System.out.println(myDataSourceBean);
    }
    @Test
    public void testUtil(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-util.xml");
        MyDataSource01 myDataSource01Bean = applicationContext.getBean("MyDataSource01Bean", MyDataSource01.class);
        System.out.println(myDataSource01Bean);
    }
    @Test
    public void testC(){
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring-review-set-c.xml");
        Customer customerBean = applicationContext.getBean("CustomerBean", Customer.class);
        System.out.println(customerBean);
    }
    @Test
    public void testP(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-p.xml");
        Customer customerBean = applicationContext.getBean("CustomerBean", Customer.class);
        System.out.println(customerBean);
    }
    @Test
    public void testNullDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-nullDI.xml");
        Vip vipBean = applicationContext.getBean("VipBean", Vip.class);
        System.out.println(vipBean);
    }
    @Test
    public void testMapDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-mapDI.xml");
        People peopleBean = applicationContext.getBean("PeopleBean", People.class);
        System.out.println(peopleBean);
    }
    @Test
    public void testArrayDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-arrDI.xml");
        Person personBean = applicationContext.getBean("PersonBean", Person.class);
        System.out.println(personBean);
    }
    @Test
    public void testLinkDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-linkDI.xml");
        Student studentBean = applicationContext.getBean("StudentBean", Student.class);
        System.out.println(studentBean);
    }
    @Test
    public void testSimpleDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-simpleDI.xml");
        User userBean = applicationContext.getBean("UserBean", User.class);
        System.out.println(userBean);
    }
    @Test
    public void testOutDI(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-review-set-outDI.xml");
        StudentServiceImpl studentServiceImplBean = applicationContext.getBean("StudentServiceImplBean", StudentServiceImpl.class);
        studentServiceImplBean.getAction();
    }
    @Test
    public void testInsideDI(){
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("spring-review-set.xml");
        StudentServiceImpl studentService = applicationContext.getBean("StudentServiceImplBean", StudentServiceImpl.class);
        studentService.getAction();
    }
}
