package com.bank.account.service;

import com.bank.account.dto.TransferDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.InsufficientBalanceExeption;
import com.bank.account.exception.InvalidTransferDetailsException;
import com.bank.account.model.Account;
import com.bank.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountService accountService = new AccountService();

    @Mock
    AccountRepository accountRepository;

    @Mock
    Account account;

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowAccountNotFoundExceptionIfFromAccountIsNull() {
        AccountService a = Mockito.spy(new AccountService());

        Mockito.doReturn(null).when(a).getAccountWithNumber(anyInt());

        TransferDto transferDto = mock(TransferDto.class);
        a.transfer(transferDto);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldThrowAccountNotFoundExceptionIfToAccountIsNull() {
        AccountService a = Mockito.spy(new AccountService());

        Mockito.doReturn(account).doReturn(null).when(a).getAccountWithNumber(anyInt());

        TransferDto transferDto = mock(TransferDto.class);
        a.transfer(transferDto);
    }

    @Test(expected = InvalidTransferDetailsException.class)
    public void shouldThrowInvalidDetailsExceptionIfAccountDetailsAreSame() {
        AccountService a = Mockito.spy(new AccountService());

        Mockito.doReturn(account).when(a).getAccountWithNumber(anyInt());

        TransferDto transferDto = mock(TransferDto.class);
        a.transfer(transferDto);
    }

    @Test
    public void shouldCallTransferMethod() {
        AccountService spyAccountService = Mockito.spy(new AccountService());
        Account toAcc = new Account(1, BigDecimal.valueOf(1));
        Account fromAcc = new Account(2, BigDecimal.valueOf(1));
        Mockito.doReturn(toAcc).doReturn(fromAcc).when(spyAccountService).getAccountWithNumber(anyInt());
        doReturn(true).when(spyAccountService).processTransfer(any(Account.class), any(Account.class), any(BigDecimal.class));
        TransferDto transferDto = mock(TransferDto.class);
        spyAccountService.transfer(transferDto);
        verify(spyAccountService, times(1)).processTransfer(any(Account.class), any(Account.class), any(BigDecimal.class));
    }


    @Test(expected = InsufficientBalanceExeption.class)
    public void shouldThrowInsufficientBalanceException() {
        Account toAcc = new Account(1, BigDecimal.valueOf(1));
        Account fromAcc = new Account(2, BigDecimal.valueOf(1));
        BigDecimal amount = BigDecimal.valueOf(200.0);
        accountService.processTransfer(fromAcc, toAcc, amount);
    }

    @Test
    public void shouldChangeAccountBalances() {
        Account toAcc = new Account(1, BigDecimal.valueOf(100));
        Account fromAcc = new Account(2, BigDecimal.valueOf(301));
        BigDecimal amount = BigDecimal.valueOf(100.0);
        accountService.processTransfer(fromAcc, toAcc, amount);
        assertThat(fromAcc.getBalance(), is(BigDecimal.valueOf(201.0)));
        assertThat(toAcc.getBalance(), is(BigDecimal.valueOf(200.0)));
        verify(accountRepository, times(1)).saveTransfer(fromAcc, toAcc);
    }

    @Test
    public void shouldAddToAccountBalance() {
        Account toAcc = new Account(1, BigDecimal.valueOf(100));
        Account fromAcc = new Account(2, BigDecimal.valueOf(301));
        BigDecimal amount = BigDecimal.valueOf(100.0);
        accountService.processTransfer(fromAcc, toAcc, amount);
        assertThat(toAcc.getBalance(), is(BigDecimal.valueOf(200.0)));
        verify(accountRepository, times(1)).saveTransfer(fromAcc, toAcc);
    }

    @Test(expected = AccountNotFoundException.class)
    public void shouldGetAccountWithNumber() {
        when(accountRepository.getAccountByUUID(anyInt())).thenReturn(null);
        accountService.getAccountWithNumber(2);
    }

    @Test
    public void shouldReturnAccountWithNumber() {
        Account account = new Account();
        when(accountRepository.getAccountByUUID(anyInt())).thenReturn(account);
        Account account1 = accountService.getAccountWithNumber(2);
        assertThat(account, equalTo(account1));
    }


}
