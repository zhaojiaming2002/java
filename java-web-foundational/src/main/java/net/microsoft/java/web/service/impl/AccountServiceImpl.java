package net.microsoft.java.web.service.impl;

import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.bean.vo.AccountVO;
import net.microsoft.java.web.dao.AccountDao;
import net.microsoft.java.web.dao.impl.AccountDaoImpl;

import net.microsoft.java.web.service.AccountService;
import net.microsoft.java.web.util.DruidDataSourceUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public boolean transferAccounts(String fromAccountName, String toAccountName, BigDecimal amount) throws Exception {
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

            System.out.println("遇到异常回滚");
            connection.rollback();
            throw new RuntimeException(e.getMessage());
        }


        return result;
    }

    /**
     * 查询jdbc_account 所有用户
     *
     * @return 返回AccountVO 集合
     * @throws Exception
     */
    @Override
    public List<AccountVO> selectAll() throws Exception {
        List<Account> accountList = accountDao.selectAll();
        if (null != accountList && accountList.size() > 0) {
            List<AccountVO> accountVOList = account2AccountVO(accountList);
            return accountVOList;
        }
        return null;
    }

    @Override
    public boolean insert(Account account) throws Exception {
        if (account != null && account.getName() != "") {
            try {

                Account accountResult = accountDao.selectOne(account);
                if (accountResult == null) {
                    boolean insert = accountDao.insert(account);
                    return insert;
                } else {
                    throw new RuntimeException("用户已经存在");
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean deleteAccountById(Long id) {

        try {
//            手动造异常
//            System.out.println(1 / 0);
            Account accountCondition = new Account(id);
            Account account = accountDao.selectOne(accountCondition);
            if (null != account) {
                return accountDao.delete(account);
            }
            throw new RuntimeException("删除失败，账户不存在");


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean updateAccountById(Account account) {
        if (null != account && account.getId() != null) {
            try {
                Account accountResult = accountDao.selectOne(new Account(account.getId()));
                if (accountResult != null) {
                    return accountDao.update(account);
                }
                throw new RuntimeException("账号不存在");

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Account findUserById(Long id) {
        try {
            Account account = accountDao.selectOne(new Account(id));
            if (account != null) {
                return account;
            }
            throw new RuntimeException("用户不存在");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<AccountVO> findAccountByPage(Integer pageNo, Integer pageSize) {
        try {
            List<Account> accountList = accountDao.selectAccountByPage(pageNo, pageSize);
            if (accountList != null && accountList.size() > 0) {
                List<AccountVO> accountVOList = account2AccountVO(accountList);
                return accountVOList;

            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;

    }

    @Override
    public Long totalCount() {
        try {
            Long totalCount = accountDao.totalCount();
            if (totalCount != null) {
                return totalCount;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public Long totalCountPage(Integer pageSize) {
        try {
            Long totalCount = accountDao.totalCount();

            return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }


    /**
     * Account 转换 AccountVO
     *
     * @param accountList
     * @return
     */
    public List<AccountVO> account2AccountVO(List<Account> accountList) {

        if (accountList != null && accountList.size() > 0) {
            List<AccountVO> accountVOList = new ArrayList<>();
            for (Account account : accountList) {
                accountVOList.add(new AccountVO(account.getId(), account.getName(), account.getBalance(),
                        account.getStatus(), account.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
                        , account.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                );
            }
            return accountVOList;
        }
        return null;
    }

    public AccountVO account2AccountVO(Account account) {

        if (account != null) {
            AccountVO accountVO = new AccountVO(account.getId(), account.getName(), account.getBalance(),
                    account.getStatus(), account.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
                    , account.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            return accountVO;
        }

        return null;
    }

    @Override
    public AccountVO findAccountByName(String name) {
        try {
            Account account = accountDao.selectOne(new Account(name));
            AccountVO accountVO = account2AccountVO(account);
            if (accountVO != null) {
                return accountVO;
            }

            throw new RuntimeException("用户不存在");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}

