package com.believesun.bank.service.impl;

import com.believesun.bank.dao.AccountDao;
import com.believesun.bank.pojo.Account;
import com.believesun.bank.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("accountService1")
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
    @Resource(name = "accountService2")
    private AccountService accountService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(Account account) {
        // 插入 第一个用户信息
        accountDao.insert(account);

        // 在这里调用 AccountServiceImpl2中的save()方法。插入第二个用户信息
        Account act_004 = new Account("act-004", 1000.0);
        accountService.save(act_004);
        return 0;
    }
}
