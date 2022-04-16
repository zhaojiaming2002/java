package net.microsoft.java.web.foundational.dao;

import net.microsoft.java.web.foundational.entity.Account;

/**
 * @description:Acount数据访问接口
 * @Date on 2022/4/16
 * @author:Suche
 **/

public interface AccountDao {

    boolean update(Account source, Account target);
}
