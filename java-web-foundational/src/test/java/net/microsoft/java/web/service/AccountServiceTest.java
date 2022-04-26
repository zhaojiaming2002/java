package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.entity.bo.AccountBO;
import net.microsoft.java.web.service.impl.AccountServiceImpl;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * @description:
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountServiceTest {
    AccountService accountService = new AccountServiceImpl();

    /**
     * 测试Service转账
     */
    @Test
    public void testTransferAccounts() {
        BigDecimal amount = new BigDecimal("100000.00");
        // 借款人
        AccountBO sourceAccount = new AccountBO();
        sourceAccount.setName("jack");
        sourceAccount.setTransactionAmount(amount);

        // 收款人
        AccountBO targetAccount = new AccountBO();
        targetAccount.setName("tony");
        targetAccount.setTransactionAmount(amount);


        boolean transferResult = accountService.transferAccounts(sourceAccount, targetAccount);
        System.out.println(transferResult ? "转账成功" : "转账失败");

    }

    /**
     * 测试根据用户名查询账户
     */
    @Test
    public void testGetAccountByName() {
        Account account = new Account();
        account.setName("tony");

        AccountBO accountBO = new AccountBO();
        account.setName(accountBO.getName());

        Account accountByName = accountService.getAccountByName(accountBO);
        System.out.println(accountByName);

    }


}
