package com.bank.account.resource;

import com.bank.account.dto.TransferDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.InsufficientBalanceExeption;
import com.bank.account.exception.InvalidTransferDetailsException;
import com.bank.account.model.Account;
import com.bank.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountResourceTest {
    @InjectMocks
    AccountResource accountResource = new AccountResource();

    @Mock
    AccountService accountService;

    @Mock
    TransferDto transferDto;

    @Test
    public void shouldReturn200OkResponseWhenAccExists() {
        Account a = new Account();
        when(accountService.getAccountWithNumber(anyInt())).thenReturn(a);
        Response response = accountResource.getAccount(2);
        assertThat((Account) response.getEntity(), is(a));
        assertThat((Response.Status) response.getStatusInfo(), is(Response.Status.OK));
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowExceptionIfAccDoesNotExist() {
        when(accountService.getAccountWithNumber(anyInt())).thenThrow(new AccountNotFoundException(""));
        accountResource.getAccount(2);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowExceptionIfAccountNotFoundOnTransfer() {
        when(accountService.transfer(transferDto)).thenThrow(new AccountNotFoundException(""));
        accountResource.transferFunds(transferDto);
    }

    @Test(expected = InvalidTransferDetailsException.class)
    public void shouldThrowExceptionIfInvalidDetails() {
        when(accountService.transfer(transferDto)).thenThrow(new InvalidTransferDetailsException(""));
        accountResource.transferFunds(transferDto);
    }

    @Test(expected = InsufficientBalanceExeption.class)
    public void shouldThrowExceptionIfInsufficientBalance() {
        when(accountService.transfer(transferDto)).thenThrow(new InsufficientBalanceExeption(""));
        accountResource.transferFunds(transferDto);
    }

    @Test
    public void shouldReturnResponseOnSuccessfulTransfer() {
        doReturn(true).when(accountService).transfer(transferDto);
        Response response = accountResource.transferFunds(transferDto);
        assertThat((Response.Status) response.getStatusInfo(), is(Response.Status.OK));

    }

}