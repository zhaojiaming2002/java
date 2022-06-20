package net.microsoft.java.web.dao;

import net.microsoft.java.web.dao.impl.AccountDaoImpl;
import net.microsoft.java.web.entity.Account;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


/**
 * @description: 账号金额实现类测试
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountDaoImplTest {
    AccountDao accountDao = new AccountDaoImpl();



    @Test
    public void testUpdate() {
        AccountDao accountDao = new AccountDaoImpl();
        try {
            boolean tony = accountDao.update( "tony", new BigDecimal("50").negate());
            System.out.println(tony);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
