package net.microsoft.java.web.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: View Object
 * @Date on 2022/6/20
 * @author: suche
 **/

public class AccountVO implements Serializable {

    public AccountVO(String name, BigDecimal balance, Integer status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public AccountVO() {
    }

    public AccountVO(Long id, String name, BigDecimal balance, Integer status, String createDate, String updateDate) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 账户余额/交易金额
     */
    private BigDecimal balance;


    /**
     * 状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建日期
     */
    private String createDate;
    /**
     * 更新日期
     */
    private String updateDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


    @Override
    public String toString() {
        return "AccountVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
