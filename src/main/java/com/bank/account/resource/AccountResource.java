package com.bank.account.resource;

import com.bank.account.dto.TransferDto;
import com.bank.account.model.Account;
import com.bank.account.service.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("accounts")
public class AccountResource {
    AccountService accountService = new AccountService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getMessage(@PathParam("accountNumber") UUID accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(Account account) {
        return accountService.addAccount(account);
    }

    @POST
    @Path("/transfers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean transferFunds(TransferDto transferDto) {
        return accountService.transfer(transferDto);
    }
}
