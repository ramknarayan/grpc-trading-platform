package org.example.exceptions;

public class UnknowUserException extends RuntimeException{
    private static final String MESSAGE ="User [id=%d] is not found";

    public UnknowUserException(Integer userId){
        super(MESSAGE.formatted(userId));
    }
}
