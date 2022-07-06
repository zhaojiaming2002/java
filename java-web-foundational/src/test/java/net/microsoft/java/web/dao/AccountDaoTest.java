package net.microsoft.java.web.dao;

import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.dao.impl.AccountDaoImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import java.util.List;


/**
 * @description: 账号金额实现类测试
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountDaoTest {
    AccountDao accountDao = new AccountDaoImpl();


    @Test
    public void testUpdate() {
        AccountDao accountDao = new AccountDaoImpl();
        try {
            boolean tony = accountDao.update("tony", new BigDecimal("50").negate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect() {
        try {
            Account tony = accountDao.select("tony");
            System.out.println(tony);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        try {
            List<Account> accounts = accountDao.selectAll();
            if (null != accounts) {
                Assert.assertEquals(accounts.size(), 7);
                System.out.println(accounts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectOne() {
        try {
            Account accountCondition = new Account(8L);
            Account account = accountDao.selectOne(accountCondition);
            System.out.println(account);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertAccount() {
        Account account = new Account("希尔斯", new BigDecimal("10000.00"));

        try {
            accountDao.insert(account);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Account account = new Account();
        account.setId(15L);
        boolean deleteResult = accountDao.delete(account);
        Assert.assertEquals(deleteResult, true);

    }

    @Test
    public void testUpdateAccountById() {
        Account account = new Account(11L, "wangbaoqiang", new BigDecimal("1000000000"), 0);
        try {
            boolean updateResult = accountDao.update(account);
            Assert.assertEquals(updateResult, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
