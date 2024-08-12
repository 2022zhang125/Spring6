package com.believesun.bank.test;

import com.believesun.bank.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTxTest {
    @Test
    public void testNoManage(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        accountService.transfer("act-001","act-002",10000.0);
        System.out.println("转账成功");
    }
}
