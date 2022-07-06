package net.microsoft.java.web.dao;


import net.microsoft.java.web.bean.entity.Account;

import java.math.BigDecimal;

import java.util.List;

/**
 * @description:Acount数据访问接口
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface AccountDao {


    /**
     * 根据转账金额更新指定账户余额
     *
     * @param accountName 账户名
     * @param amount      转账金额
     * @return
     * @throws Exception
     */
    boolean update(String accountName, BigDecimal amount) throws Exception;

    /**
     * 根据用户名 查询用户
     *
     * @param accountName 账户名
     * @return
     * @throws Exception
     */
    Account select(String accountName) throws Exception;

    /**
     * 查询全部用户
     *
     * @return
     * @throws Exception
     */
    List<Account> selectAll() throws Exception;

    /**
     * 查询一个账户
     *
     * @param accountCondition
     * @return
     * @throws Exception
     */
    Account selectOne(Account accountCondition) throws Exception;


    /**
     * 插入一个账户
     *
     * @param account 账户
     * @return
     * @throws Exception
     */
    boolean insert(Account account) throws Exception;

    /**
     * 删除一个用户
     *
     * @param account 账户
     * @return
     * @throws Exception
     */

    boolean delete(Account account);

    /**
     * 更新用户信息
     *
     * @param account
     * @return
     * @throws Exception
     */
    boolean update(Account account) throws Exception;

}
