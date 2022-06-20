package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.dao.impl.AccountDaoImpl;
import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.service.AccountService;
import net.microsoft.java.web.util.DruidDataSourceUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: AccountService实现类 逻辑判断都是在Service层，数据准备是在Dao层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountServiceImpl implements AccountService {
    /**
     * 依赖 DAO层
     */
    AccountDao accountDao = new AccountDaoImpl();


    @Override
    public boolean transferAccounts(String fromAccountName, String toAccountName, BigDecimal amount) {
        System.out.println(Thread.currentThread().getName() + "当前处理请求的线程名");
        boolean result = false;
        Connection connection = null;
        String transferMessage = "";
        try {
            // 业务判断 判断用户是否存在
            Account fromAccount = accountDao.select(fromAccountName);
            Account toAccount = accountDao.select(toAccountName);
            connection = DruidDataSourceUtil.getConnection();
            if (fromAccount != null && toAccount != null) {
                // 判断转出账户余额大于0
                if (fromAccount.getBalance().compareTo(BigDecimal.ZERO) == 1) {
                    // 转出账户余额大于或者等于转账的金额
                    if (fromAccount.getBalance().compareTo(amount) == 1 || fromAccount.getBalance().compareTo(amount) == 0) {
                        // 开启事物
                        connection.setAutoCommit(false);

                        // 执行转账操作
                        // 1.转出账户金额减去交易金额
                        boolean resultUpdate = accountDao.update(fromAccountName, amount);
                        if (resultUpdate) {
                            // 2. 转出金额加到转入账户上
                            resultUpdate = accountDao.update(toAccountName, amount.negate());
                            if (resultUpdate) {
                                // 3.转账成功
                                connection.commit();
                                result = true;
                            }
                        }

                    } else {
                        transferMessage = "转出账户余额必须大于或者等于转账的金额";
                    }
                } else {
                    transferMessage = "判断转出账户余额大于0";
                }
            } else {
                transferMessage = "转出账户或转入账户不存在";
            }
            if ("" != transferMessage) {
                throw new RuntimeException(transferMessage);
            }

        } catch (Exception e) {
            try {
                e.printStackTrace();
                System.out.println("遇到异常回滚");

                connection.rollback();
                throw new RuntimeException(e.getMessage());
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }


        return result;
    }


}
