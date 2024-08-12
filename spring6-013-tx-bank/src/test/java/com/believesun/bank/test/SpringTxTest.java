package com.believesun.bank.test;

import com.believesun.bank.Spring6Config;
import com.believesun.bank.pojo.Account;
import com.believesun.bank.service.AccountService;
import com.believesun.bank.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTxTest {
    @Test
    public void testNoXML() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring6Config.class);
        // 因为是有接口的，所以默认使用JDK的代理模式，所以必须要用AccountService.class而不是其实现类
        AccountService accountService = applicationContext.getBean("accountService1", AccountService.class);
        Account act_003 = new Account("act-003", 10000.0);
        accountService.save(act_003);
    }

    @Test
    public void testPropagationRequired(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        Account act_003 = new Account("act-003",10000.0);
        accountService.save(act_003);
    }
    @Test
    public void testNoManage(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        accountService.transfer("act-001","act-002",10000.0);
        System.out.println("转账成功");
    }
}
