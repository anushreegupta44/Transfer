package com.bank.account.exception;

import com.bank.account.dto.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTransferDetailsExceptionMapper implements ExceptionMapper<InvalidTransferDetailsException> {
    @Override
    public Response toResponse(InvalidTransferDetailsException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
        return Response
            .status(Response.Status.NOT_FOUND)
            .entity(errorMessage)
            .build();
    }
}
