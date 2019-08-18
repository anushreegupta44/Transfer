package com.bank.account.repository;

import com.bank.account.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountRepository {
    private static Map<UUID,Account> accounts = new HashMap<>();

    public static Map<UUID, Account> getAllAccounts() {
        return accounts;
    }
}
