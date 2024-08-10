package org.example.user.tests.service.advice;

import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.example.user.tests.exceptions.InsufficientBalanceException;
import org.example.user.tests.exceptions.InsufficientSharesException;
import org.example.user.tests.exceptions.UnknowUserException;
import org.example.user.tests.exceptions.UnknownTickerException;

@GrpcAdvice
public class ServiceExceptionHandler {
    @GrpcExceptionHandler(UnknownTickerException.class)
    public Status handeInvalidArguments(UnknownTickerException e){
        return Status.INVALID_ARGUMENT.withDescription(e.getMessage());
    }
    @GrpcExceptionHandler(UnknowUserException.class)
    public Status handeInvalidEntities(UnknowUserException e){
        return Status.NOT_FOUND.withDescription(e.getMessage());
    }
    @GrpcExceptionHandler({InsufficientBalanceException.class, InsufficientSharesException.class})
    public Status handePreCodition(Exception e){
        return Status.FAILED_PRECONDITION.withDescription(e.getMessage());
    }
}
