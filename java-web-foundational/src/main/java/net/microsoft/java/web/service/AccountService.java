package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.entity.bo.AccountBO;

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
    boolean transferAccounts(AccountBO sourceAccount, AccountBO targetAccount);


    /**
     * 根据用户名获取账户
     *
     * @param account
     * @return
     */
    Account getAccountByName(AccountBO account);
}
