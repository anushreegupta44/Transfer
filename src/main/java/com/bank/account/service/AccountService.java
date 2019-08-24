package com.bank.account.service;

import com.bank.account.dto.TransferDto;
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

//    public List<Account> getAllAccounts() {
//    }

    public Account addAccount(Account acc) {
        return acc;
    }

//    public Account getAccount(UUID accountNumber) {
//        return accounts.get(accountNumber);
//    }

    public Boolean transfer(TransferDto transferDto) {
        Account from = accountRepository.getAccountByUUID(transferDto.getFromAccNum());
        if (from == null) {
//            throw AccountNotFoundException();
        }
        Account to = accountRepository.getAccountByUUID(transferDto.getToAccNum());
        if (to == null) {
//            throw AccountNotFoundException();
        }
        if (from == to) {
//            throw
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
//            throw
        }
        from.setBalance(fromNewBal);
        BigDecimal toNewBal = to.getBalance().add(amount);
        to.setBalance(toNewBal);
        return true;
    }


}
