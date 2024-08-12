package com.believesun.bank.service.impl;

import com.believesun.bank.dao.AccountDao;
import com.believesun.bank.pojo.Account;
import com.believesun.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService2")
public class AccountServiceImpl2 implements AccountService {
    @Override
    public void transfer(String fromActno, String toActno, Double money) {

    }
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(Account account) {
        // 保存用户信息
    return accountDao.insert(account);
    }
}
