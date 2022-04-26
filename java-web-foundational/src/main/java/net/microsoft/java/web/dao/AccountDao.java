package net.microsoft.java.web.dao;

import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.entity.bo.AccountBO;

import java.util.List;

/**
 * @description:Acount数据访问接口
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface AccountDao {
    /**
     * @param sourceAccount
     * @param targetAccount
     * @return
     */
    boolean update(AccountBO sourceAccount, AccountBO targetAccount);

    List<Account> select(Account userCondition);
}
