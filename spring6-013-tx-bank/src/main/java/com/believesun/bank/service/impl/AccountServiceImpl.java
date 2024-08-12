package com.believesun.bank.service.impl;

import com.believesun.bank.dao.AccountDao;
import com.believesun.bank.pojo.Account;
import com.believesun.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource(name = "accountDao")
    private AccountDao accountDao;
    public void transfer(String fromActno,String toActno,Double money){
        // 判断转出账户余额是否充足
        Account fromAct = accountDao.selectByActno(fromActno);
        if (money > fromAct.getBalance()) {
            throw new RuntimeException("余额不足！！！");
        }
        // 余额充足的情况下
        Account toAct = accountDao.selectByActno(toActno);

        // 内存更新
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(money + toAct.getBalance());

        // 数据库更新
        int update = accountDao.update(fromAct);
        /*String s = null;
        s.toString();*/
        update+= accountDao.update(toAct);

        if (update!=2) {
            throw new RuntimeException("转账异常！！！");
        }
    }
}
