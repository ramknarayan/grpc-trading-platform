package org.example.user.tests.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public static final String MESSAGE ="user [id=%d] does not have enough funds to compelte the transaction ";
   public   InsufficientBalanceException(Integer userId){
       super(MESSAGE.formatted(userId));
   }
}
