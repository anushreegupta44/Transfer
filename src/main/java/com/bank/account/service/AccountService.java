package com.bank.account.service;

import com.bank.account.dto.TransferDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.InsufficientBalanceExeption;
import com.bank.account.exception.InvalidTransferDetailsException;
import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;

import java.math.BigDecimal;

public class AccountService {
    AccountRepository accountRepository = new AccountRepository();

    public AccountService() {
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Boolean transfer(TransferDto transferDto) {
        Account from = accountRepository.getAccountByUUID(transferDto.getFromAccNum());
        if (from == null) {
            throw new AccountNotFoundException("Account with number " + transferDto.getFromAccNum() + " not found");
        }
        Account to = accountRepository.getAccountByUUID(transferDto.getToAccNum());
        if (to == null) {
            throw new AccountNotFoundException("Account with number " + transferDto.getToAccNum() + " not found");
        }
        if (from.getAccountNumber().equals(to.getAccountNumber())) {
            throw new InvalidTransferDetailsException("You cannot transfer money to your own account");
        }
        transfer(from, to, transferDto.getAmount());
        accountRepository.saveTransfer(from, to);
        return true;
    }

    public Boolean transfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromNewBal = from.getBalance();
        if (from.getBalance().compareTo(amount) == 1 || from.getBalance().compareTo(amount) == 0) {
            fromNewBal = from.getBalance().subtract(amount);
        } else {
            throw new InsufficientBalanceExeption("The account you're transferring money from has insufficient balance");
        }
        from.setBalance(fromNewBal);
        BigDecimal toNewBal = to.getBalance().add(amount);
        to.setBalance(toNewBal);
        return true;
    }


}
