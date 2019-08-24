package com.bank.account;

import com.bank.account.resource.AccountResource;
import com.bank.account.service.AccountService;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {DIModule.class})
@Singleton
public interface TransferApplicationComponent {
    AccountResource getAccountResource();

    AccountService getAccountService();

}
