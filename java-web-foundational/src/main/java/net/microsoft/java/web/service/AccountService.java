package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @description:AccountService业务逻辑层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface AccountService {

    /**
     * 转账
     *
     * @param sourceAccount
     * @param targetAccount
     * @return
     */

    /**
     * 账户转账
     *
     * @param fromAccountName 转出账户名
     * @param toAccountName   转入账户名
     * @param amount          转出金额
     * @return 是否转账成功
     */
    boolean transferAccounts(String fromAccountName, String toAccountName, BigDecimal amount);

    /**
     * 根据用户名获取账户
     *
     * @param account
     * @return
     */

}
