package com.bank.account.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferDto {
    private UUID fromAccNum;
    private UUID toAccNum;
    private BigDecimal amount;

    public TransferDto() {
    }

    public UUID getFromAccNum() {
        return fromAccNum;
    }

    public void setFromAccNum(UUID fromAccNum) {
        this.fromAccNum = fromAccNum;
    }

    public UUID getToAccNum() {
        return toAccNum;
    }

    public void setToAccNum(UUID toAccNum) {
        this.toAccNum = toAccNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
