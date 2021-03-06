package com.bank.account.service;

import com.bank.account.dto.TransferDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.InsufficientBalanceExeption;
import com.bank.account.exception.InvalidTransferDetailsException;
import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;

import javax.inject.Inject;
import java.math.BigDecimal;

public class AccountService {
    AccountRepository accountRepository;

    public AccountService() {
    }

    @Inject
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Boolean transfer(TransferDto transferDto) {
        Account from = getAccountWithNumber(transferDto.getFromAccNum());
        if (from == null) {
            throw new AccountNotFoundException("Account with number " + transferDto.getFromAccNum() + " not found");
        }
        Account to = getAccountWithNumber(transferDto.getToAccNum());
        if (to == null) {
            throw new AccountNotFoundException("Account with number " + transferDto.getToAccNum() + " not found");
        }
        if (from.getAccountNumber().equals(to.getAccountNumber())) {
            throw new InvalidTransferDetailsException("You cannot transfer money to your own account");
        }
        processTransfer(from, to, transferDto.getAmount());
        return true;
    }

    public Boolean processTransfer(Account from, Account to, BigDecimal amount) {
        BigDecimal fromNewBal = from.getBalance();
        if (from.getBalance().compareTo(amount) == 1 || from.getBalance().compareTo(amount) == 0) {
            fromNewBal = from.getBalance().subtract(amount);
        } else {
            throw new InsufficientBalanceExeption("The account you're transferring money from has insufficient balance");
        }
        from.setBalance(fromNewBal);
        BigDecimal toNewBal = to.getBalance().add(amount);
        to.setBalance(toNewBal);
        accountRepository.saveTransfer(from, to);
        return true;
    }


    public Account getAccountWithNumber(Integer accountNum) {
        Account acc = accountRepository.getAccountByUUID(accountNum);
        if (acc == null) {
            throw new AccountNotFoundException("The account number " + accountNum + " is invalid");
        }
        return acc;
    }
}
