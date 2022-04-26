package net.microsoft.java.web.dao.impl;

import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.entity.Account;
import net.microsoft.java.web.entity.bo.AccountBO;
import net.microsoft.java.web.util.CustomerDataSourceV2;
import net.microsoft.java.web.util.DruidDataSourceUtil;
import net.microsoft.java.web.util.JDBCUtil;

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
    @Override
    public boolean update(AccountBO sourceAccount, AccountBO targetAccount) {
//        CustomerDataSourceV1 customerDataSourceV1 = new CustomerDataSourceV1();
//        CustomerDataSourceV2 customerDataSourceV2 = new CustomerDataSourceV2();

        Connection connection = null;
        PreparedStatement sourcePreparedStatement = null;
        PreparedStatement targetPreparedStatement = null;
        String sqlSource = null;
        String sqlTarget = null;
        if (null != sourceAccount && null != sourceAccount.getName() && sourceAccount.getTransactionAmount() != null) {
            sqlSource = "update jdbc_account set balance = balance - ? where name = ? ";
        }
        if (null != targetAccount && null != targetAccount.getName() && null != targetAccount.getTransactionAmount()) {
            sqlTarget = "update jdbc_account set balance = balance + ? where name = ? ";
        }

        if (null != sqlSource && sqlSource != "" && null != sqlTarget && sqlTarget != "") {
            try {

                connection = DruidDataSourceUtil.getConnection();
                connection.setAutoCommit(false);
                sourcePreparedStatement = connection.prepareStatement(sqlSource);
                targetPreparedStatement = connection.prepareStatement(sqlTarget);

                if (null != sourceAccount && null != sourceAccount.getName() && sourceAccount.getTransactionAmount() != null
                        && null != targetAccount && targetAccount.getName() != "" && targetAccount.getTransactionAmount() != null
                ) {
                    sourcePreparedStatement.setBigDecimal(1, sourceAccount.getTransactionAmount());
                    sourcePreparedStatement.setString(2, sourceAccount.getName());
                    int row = sourcePreparedStatement.executeUpdate();
                    if (row == 1) {
                        targetPreparedStatement.setBigDecimal(1, targetAccount.getTransactionAmount());
                        targetPreparedStatement.setString(2, targetAccount.getName());
                        row = targetPreparedStatement.executeUpdate();
                        connection.commit();
                        return row == 1 ? true : false;
                    }

                }

            } catch (Exception ex) {
                try {
                    connection.rollback();
                    System.out.println("发生异常，回滚");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } finally {
                DruidDataSourceUtil.release(null, sourcePreparedStatement, connection);
                DruidDataSourceUtil.release(null, targetPreparedStatement, connection);
//                customerDataSourceV1.giveBackConnection(connection);
                System.out.println("释放资源成功");
            }


        }

        return false;
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
