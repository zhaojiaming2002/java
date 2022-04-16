package net.microsoft.java.web.foundational.entity.bo;

import java.math.BigDecimal;

/**
 * @description:AccountBO
 * @Date on 2022/4/16
 * @author:Suche
 **/

public class AccountBO {
    private String name;
    private BigDecimal transactionAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
