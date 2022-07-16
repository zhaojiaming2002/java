package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.util.CustomerQueryRunner;
import net.microsoft.java.web.util.DruidDataSourceUtil;


import java.math.BigDecimal;
import java.sql.*;
import java.util.List;


/**
 * @description:Account接口的实现类
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountDaoImpl implements AccountDao {

    /**
     * 组合 Dbutils
     */
    private CustomerQueryRunner queryRunner = new CustomerQueryRunner(DruidDataSourceUtil.getDataSource());


    @Override
    public boolean update(String accountName, BigDecimal amount) throws Exception {
        System.out.println(Thread.currentThread().getName() + "当前处理请求的线程名");
        String sql = "update jdbc_account set balance = balance- ?,update_date = now() where name  = ?";
        Connection connection = DruidDataSourceUtil.getConnection();
        int row = queryRunner.update(connection, sql, amount, accountName);

        return row == 1 ? true : false;
    }

    @Override
    public Account select(String accountName) throws Exception {
        String sql = null;
        if (accountName != null && accountName != "") {
            sql = "select id,name,balance,create_date,update_date from jdbc_account where name = ?";
            Account account = queryRunner.queryForBean(sql, Account.class, accountName);
            if (account != null) {
                return account;
            }
        }
        return null;
    }


    @Override
    public List<Account> selectAll() throws Exception {
        String sql = "select id,name,balance,status,create_date,update_date from jdbc_account";
        List<Account> accountList = queryRunner.queryForBeans(sql, Account.class);
        if (null != accountList && accountList.size() > 0) {
            return accountList;
        }

        return null;
    }

    @Override
    public Account selectOne(Account accountCondition) throws Exception {
        String sql = null;
        if (null != accountCondition && accountCondition.getName() != null && accountCondition.getName() != "") {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where name = ?";
            Account account = queryRunner.queryForBean(sql, Account.class, accountCondition.getName());
            if (account != null) {
                return account;
            }
        } else if (null != accountCondition && accountCondition.getId() != null) {
            sql = "select id,name,balance,status,create_date,update_date from jdbc_account where id = ?";
            Account account = queryRunner.queryForBean(sql, Account.class, accountCondition.getId());
            if (null != account) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean insert(Account account) throws Exception {
        String sql = "insert into jdbc_account values(null,?,?,?,now(),now())";
        int row = queryRunner.update(sql, account.getName(), account.getBalance(), account.getStatus());
        return row == 1 ? true : false;
    }

    @Override
    public boolean delete(Account account) {
        if (null != account && null != account.getId()) {
            String sql = "delete from jdbc_account where id = ?";
            try {
                int row = queryRunner.update(sql, account.getId());
                return row == 1 ? true : false;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean update(Account account) throws Exception {
        if (null != account && account.getId() != null) {
            String sql = "update jdbc_account set name=?,balance=?,status=?,update_date=now() where id=?";
            int row = queryRunner.update(sql, account.getName(), account.getBalance(), account.getStatus(), account.getId());
            return row == 1 ? true : false;
        }
        return false;
    }

    @Override
    public Long totalCount() throws Exception {
        String sql = "select count(*) from jdbc_account";
        Long totalCountResult = queryRunner.queryForType(sql, Long.class);
        if (totalCountResult != null) {
            return totalCountResult;
        }

        return null;
    }

    @Override
    public List<Account> selectAccountByPage(Integer pageNo, Integer pageSize) throws Exception {
        String sql = "select id,name,balance,status,create_date,update_date from jdbc_account limit ?,?";
        List<Account> accountListByPage = queryRunner.queryForBeans(sql, Account.class, (pageNo - 1) * pageSize, pageSize);
        if (accountListByPage != null && accountListByPage.size() > 0) {
            return accountListByPage;
        }
        return null;
    }

}
