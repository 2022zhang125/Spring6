package com.believesun.spring6.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public void transfer(){
        System.out.println("用户转账中....");
        throw new RuntimeException("出错");
    }
    public void withdraw(){
        System.out.println("用户充值中....");
    }
}
