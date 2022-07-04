package net.microsoft.java.web.service;

import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.bean.vo.AccountVO;
import net.microsoft.java.web.service.impl.AccountServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.resource.spi.ApplicationServerInternalException;
import java.math.BigDecimal;
import java.util.List;

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


        try {
            boolean b = accountService.transferAccounts(fromAccountName, toAccountName, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void selectAll() {
        try {
            List<AccountVO> accountVOList = accountService.selectAll();
            Assert.assertEquals(accountVOList.size(), 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testInsert() {
        Account account = new Account("test", new BigDecimal("100000.00"));
        try {
            boolean insert = accountService.insert(account);
            Assert.assertEquals(insert, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
