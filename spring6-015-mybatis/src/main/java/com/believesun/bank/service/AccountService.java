package com.believesun.bank.service;

import com.believesun.bank.pojo.Account;

import java.util.List;

public interface AccountService {
    /**
     * 开户
     * @param act 账号
     * @return 成功条数
     */
    int save(Account act);

    /**
     * 根据账号销户
     *
     * @param actno 账号
     * @return 成功条数
     */
    int deleteByActno(String actno);

    /**
     * 修改账户
     *
     * @param act 用户对象
     * @return 成功条数
     */
    int update(Account act);

    /**
     * 根据账号获取账户
     *
     * @param actno 账号
     * @return 对象
     */
    Account getByActno(String actno);

    /**
     * 获取所有账户
     *
     * @return Account对象
     */
    List<Account> getAll();

    /**
     * 转账
     *
     * @param fromActno 转出的账户
     * @param toActno 转入的账号
     * @param money 转出金额
     */
    void transfer(String fromActno, String toActno, double money);
}
