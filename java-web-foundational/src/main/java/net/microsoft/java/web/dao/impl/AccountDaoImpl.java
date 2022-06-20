package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.util.DruidDataSourceUtil;
import net.microsoft.java.web.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
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
    private QueryRunner queryRunner = new QueryRunner(DruidDataSourceUtil.getDataSource());


    @Override
    public boolean update(String accountName, BigDecimal amount) throws SQLException {
        System.out.println(Thread.currentThread().getName() + "当前处理请求的线程名");
        String sql = "update jdbc_account set balance = balance- ?,update_date = now() where name  = ?";
        Connection connection = DruidDataSourceUtil.getConnection();
        int row = queryRunner.update(connection, sql, amount, accountName);

        return row == 1 ? true : false;
    }

    @Override
    public Account select(String accountName) throws SQLException {
        String sql = null;
        if (accountName != null && accountName != "") {
            sql = "select id,name,balance,create_date,update_date from jdbc_account where name = ?";
            Account account = queryRunner.query(sql, new BeanHandler<>(Account.class), accountName);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> select(Account AccountCondition) {
        String sql = null;
        if (AccountCondition != null && AccountCondition.getName() != null) {
            sql = "select id,name,balance,create_date,update_date from jdbc_account where name = ?";
        }
        if (sql != null && sql != "") {
            try (
                    Connection connection = JDBCUtil.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)

            ) {
                preparedStatement.setString(1, AccountCondition.getName());
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Account> accountList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    Timestamp createDate = resultSet.getTimestamp("create_date");
                    Timestamp updateDate = resultSet.getTimestamp("update_date");
                    accountList.add(new Account(id, name, balance, createDate, updateDate));
                }
                if (accountList != null && accountList.size() > 0) {
                    return accountList;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
