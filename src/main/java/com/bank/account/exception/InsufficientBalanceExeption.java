package com.bank.account.exception;

public class InsufficientBalanceExeption extends RuntimeException {
    public InsufficientBalanceExeption(String message) {
        super(message);
    }
}
