package com.bank.account.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.UUID;

@XmlRootElement
public class Account {
    private UUID accountNumber;
    private BigDecimal balance;

    public Account() {
    }

    public Account(UUID accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
