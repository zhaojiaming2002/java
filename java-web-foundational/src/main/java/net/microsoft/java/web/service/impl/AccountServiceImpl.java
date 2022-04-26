package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.dao.impl.AccountDaoImpl;
import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.entity.bo.AccountBO;
import net.microsoft.java.web.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: AccountService实现类 逻辑判断都是在Service层，数据准备是在Dao层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDaoImpl();


    @Override
    public boolean transferAccounts(AccountBO sourceAccountBO, AccountBO targetAccountBO) {
        // 转账之前先查询借款人有没有

        Account accountByName = getAccountByName(sourceAccountBO);
        Account targetByName = getAccountByName(targetAccountBO);

        if (accountByName != null && targetByName != null && accountByName.getBalance().compareTo(BigDecimal.ZERO) == 0
                || accountByName.getBalance().compareTo(targetAccountBO.getTransactionAmount()) == -1
        ) {
            System.out.println(accountByName.getName() + "金额不足");
            return false;
        } else if (
                accountByName.getBalance().compareTo(targetAccountBO.getTransactionAmount()) == 0 ||
                        accountByName.getBalance().compareTo(targetAccountBO.getTransactionAmount()) == 1) {
            return accountDao.update(sourceAccountBO, targetAccountBO);
        }
        return false;
    }

    @Override
    public Account getAccountByName(AccountBO accountBO) {

        Account account = new Account();
        account.setName(accountBO.getName());

        if (account != null && account.getName() != null && account.getName() != "") {
            List<Account> select = accountDao.select(account);
            return select.get(0);
        }
        return null;
    }
}
