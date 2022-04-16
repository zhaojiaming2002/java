package net.microsoft.java.web.foundational.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @description:
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class Account {
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 账户余额/交易金额
     */
    private BigDecimal balance;

    /**
     * 创建日期
     */
    private Timestamp createDate;
    /**
     * 更新日期
     */
    private Timestamp updateDate;

    public Account() {
    }

    public Account(int id, String name, BigDecimal balance, Timestamp createDate, Timestamp updateDate) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
