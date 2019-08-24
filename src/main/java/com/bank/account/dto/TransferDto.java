package com.bank.account.dto;

import java.math.BigDecimal;

public class TransferDto {
    private Integer fromAccNum;
    private Integer toAccNum;
    private BigDecimal amount;

    public TransferDto() {
    }

    public Integer getFromAccNum() {
        return fromAccNum;
    }

    public void setFromAccNum(Integer fromAccNum) {
        this.fromAccNum = fromAccNum;
    }

    public Integer getToAccNum() {
        return toAccNum;
    }

    public void setToAccNum(Integer toAccNum) {
        this.toAccNum = toAccNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
