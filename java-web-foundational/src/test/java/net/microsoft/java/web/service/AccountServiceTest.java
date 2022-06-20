package net.microsoft.java.web.service;

import net.microsoft.java.web.entity.Account;
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
     * 测试根据用户名查询账户
     */
    @Test
    public void testGetAccountByName() {
        Account account = new Account();
        account.setName("tony");




    }

    @Test
    public void testTransferAccounts() {
        String fromAccountName = "tony";
        String toAccountName = "jack";
        BigDecimal amount = new BigDecimal("500");


        boolean  b = accountService.transferAccounts(fromAccountName, toAccountName, amount);


    }


}
