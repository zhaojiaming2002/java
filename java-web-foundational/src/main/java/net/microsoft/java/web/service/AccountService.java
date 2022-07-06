package net.microsoft.java.web.service;


import net.microsoft.java.web.bean.entity.Account;
import net.microsoft.java.web.bean.vo.AccountVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:AccountService业务逻辑层
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface AccountService {

    /**
     * 转账
     *
     * @param sourceAccount
     * @param targetAccount
     * @return
     */

    /**
     * 账户转账
     *
     * @param fromAccountName 转出账户名
     * @param toAccountName   转入账户名
     * @param amount          转出金额
     * @return 是否转账成功
     * @throws Exception
     */
    boolean transferAccounts(String fromAccountName, String toAccountName, BigDecimal amount) throws Exception;

    /**
     * 查询全部用户
     *
     * @return
     * @throws Exception
     */
    List<AccountVO> selectAll() throws Exception;

    /**
     * 添加一个用户
     *
     * @param account
     * @return
     * @throws Exception
     */
    boolean insert(Account account) throws Exception;

    /**
     * 根据Id删除用户
     *
     * @param id
     * @return
     */
    boolean deleteAccountById(Long id);

    /**
     * 根据Id更新用户
     * @param account
     * @return
     */
    boolean updateAccountById(Account account);

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    Account findUserById(Long id);

}
