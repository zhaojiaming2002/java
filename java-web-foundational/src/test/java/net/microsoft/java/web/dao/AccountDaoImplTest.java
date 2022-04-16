package net.microsoft.java.web.dao;

import net.microsoft.java.web.dao.impl.AccountDaoImpl;
import net.microsoft.java.web.foundational.entity.Account;
import net.microsoft.java.web.foundational.entity.bo.AccountBO;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;


/**
 * @description: 账号金额实现类测试
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountDaoImplTest {
    AccountDao accountDao = new AccountDaoImpl();

    @Test
    public void testUpdateAmount() {

        BigDecimal amount = new BigDecimal("100000.00");
        // 借款人
        AccountBO sourceAccount = new AccountBO();
        sourceAccount.setName("tony");
        sourceAccount.setTransactionAmount(amount);

        // 收款人
        AccountBO targetAccount = new AccountBO();
        targetAccount.setName("jack");
        targetAccount.setTransactionAmount(amount);

        accountDao.update(sourceAccount, targetAccount);
    }

    @Test
    public void testSelect() {
        Account account = new Account();
        account.setName("jack");
        List<Account> accountSelects = accountDao.select(account);

        for (Account accountSelect : accountSelects) {
            System.out.println(accountSelect.getName() + " " + accountSelect.getBalance());
        }
    }

}
