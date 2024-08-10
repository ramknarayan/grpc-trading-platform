package org.example.user.tests.exceptions;

public class InsufficientSharesException extends RuntimeException{
    public static final String MESSAGE ="User [id = %d] does not have enough shares to complete the transcation ";
public InsufficientSharesException(Integer userId){
    super(MESSAGE.formatted(userId));
}

}
