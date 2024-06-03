package com.picpay.picpay.transaction;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message){
        super(message);
    }

}
