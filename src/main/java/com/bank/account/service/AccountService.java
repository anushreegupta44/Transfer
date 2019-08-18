package com.bank.account.service;

import com.bank.account.dto.TransferDto;
import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AccountService {

    Map<UUID, Account> accounts = AccountRepository.getAllAccounts();

    public AccountService() {
        Account a1 = new Account(UUID.randomUUID(), new BigDecimal(200));
        Account a2 = new Account(UUID.randomUUID(), new BigDecimal(200));
        Account a3 = new Account(UUID.randomUUID(), new BigDecimal(200));
        System.out.println(a1.getAccountNumber());
        System.out.println(a2.getAccountNumber());
        System.out.println(a3.getAccountNumber());
        accounts.put(a1.getAccountNumber(), a1);
        accounts.put(a2.getAccountNumber(), a2);
        accounts.put(a3.getAccountNumber(), a3);

    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public Account addAccount(Account acc) {
        acc.setAccountNumber(UUID.randomUUID());
        accounts.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public Account getAccount(UUID accountNumber) {
        return accounts.get(accountNumber);
    }

    public Boolean transfer(TransferDto transferDto) {
        Account from = accounts.get(transferDto.getFromAccNum());
        if(from==null){
//            throw AccountNotFoundException();
        }
        Account to = accounts.get(transferDto.getToAccNum());
        if(to==null){
//            throw AccountNotFoundException();
        }
        if(from == to){
//            throw
        }
        transfer(from, to, transferDto.getAmount());
        return true;
    }

    public Boolean transfer(Account from, Account to, BigDecimal amount) {

        return true;
    }


}
