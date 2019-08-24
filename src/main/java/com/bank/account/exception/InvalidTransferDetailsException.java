package com.bank.account.exception;

public class InvalidTransferDetailsException extends RuntimeException {
    public InvalidTransferDetailsException(String message) {
        super(message);
    }
}
