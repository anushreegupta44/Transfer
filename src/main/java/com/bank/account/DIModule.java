package com.bank.account;

import com.bank.account.repository.AccountRepository;
import com.bank.account.service.AccountService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DIModule {
    @Provides
    @Singleton
    AccountRepository provideAccountRepository() {
        return new AccountRepository();
    }

    @Provides
    @Singleton
    AccountService provideAccountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }

}
