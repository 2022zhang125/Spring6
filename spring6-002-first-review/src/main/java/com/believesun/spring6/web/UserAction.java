package com.believesun.spring6.web;

import com.believesun.spring6.service.UserService;
import com.believesun.spring6.service.impl.UserServiceImpl;

public class UserAction {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.UserServiceDelete();
    }
}
