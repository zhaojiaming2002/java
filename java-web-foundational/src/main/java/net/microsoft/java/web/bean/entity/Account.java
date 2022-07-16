package net.microsoft.java.web.bean.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class Account {
    /**
     * 名称
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal balance;

    public Account(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    public Account() {
    }

    public Account(String name, BigDecimal balance, Integer status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public Account(Long id, String name, BigDecimal balance, Integer status) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(Long id, String name, BigDecimal balance, Integer status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
