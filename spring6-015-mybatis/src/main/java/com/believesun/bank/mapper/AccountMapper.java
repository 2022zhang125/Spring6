package com.believesun.bank.mapper;

import com.believesun.bank.pojo.Account;

import java.util.List;
public interface AccountMapper {
    /**
     * 新增用户信息
     * @param account 用户对象
     * @return 新增条数
     */
    int insert(Account account);

    /**
     * 根据Actno删除用户信息
     * @param actno 账号
     * @return 成功条数
     */
    int deleteByActno(String actno);

    /**
     * 根据账号更新用户数据
     * @param account 待更新用户
     * @return 成功条数
     */
    int update(Account account);

    /**
     * 查询对象通过其账号
     * @param actno 账号
     * @return 查询的对象
     */
    Account selectByActno(String actno);

    /**
     * 查询所有用户信息
     * @return List集合中存入所有的用户信息
     */
    List<Account> selectAll();
}
