package com.believesun.bank.service;

import com.believesun.bank.pojo.Account;

public interface AccountService {
    void transfer(String fromActno,String toActno,Double money);
    int save(Account account);
}
