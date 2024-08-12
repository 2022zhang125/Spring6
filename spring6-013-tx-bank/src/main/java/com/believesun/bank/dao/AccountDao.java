package com.believesun.bank.dao;

import com.believesun.bank.pojo.Account;

public interface AccountDao {
    /**
     * 根据Actno查询对象
     * @param actno 账号
     * @return 对象信息
     */
    Account selectByActno(String actno);

    /**
     * 更新Account对象
     * @param account 待更新对象
     * @return 成功条数
     */
    int update(Account account);
}
