package com.believesun.bank.test;

import com.believesun.bank.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountMapperTest {
    @Autowired
    private AccountService accountService;
    @Test
    public void testInsert(){
        accountService.transfer("act-001","act-002",1000.0);
        System.out.println("转账成功");
    }
}
