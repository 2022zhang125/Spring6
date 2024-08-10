package com.believesun.spring6.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void login(){
        System.out.println("正在验证用户信息...");
        // 抛出异常
        throw new RuntimeException("异常通知啦！！！！");
    }
}
