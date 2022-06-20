package net.microsoft.java.web.dao;

import net.microsoft.java.web.entity.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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
     */
    boolean update(String accountName, BigDecimal amount) throws SQLException;

    /**
     * 根据用户名 查询用户
     *
     * @param accountName 账户名
     * @return
     */
    Account select(String accountName) throws SQLException;

    List<Account> select(Account userCondition);

}
