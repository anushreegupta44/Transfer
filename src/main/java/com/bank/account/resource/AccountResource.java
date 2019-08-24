package com.bank.account.resource;

import com.bank.account.dto.TransferDto;
import com.bank.account.model.Account;
import com.bank.account.service.AccountService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts")
public class AccountResource {
    AccountService accountService = new AccountService();

    @POST
    @Path("/transfers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferFunds(TransferDto transferDto) {
        accountService.transfer(transferDto);
        return Response
            .status(Response.Status.OK)
            .build();
    }
}
