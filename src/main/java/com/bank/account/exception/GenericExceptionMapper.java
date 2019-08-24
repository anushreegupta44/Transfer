package com.bank.account.exception;

import com.bank.account.dto.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(throwable.getMessage(), 500);
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(errorMessage)
            .build();
    }
}
