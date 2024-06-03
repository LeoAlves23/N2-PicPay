package com.picpay.picpay.authorization;

public class UnauhotizedTransactionException extends RuntimeException{
    
    public UnauhotizedTransactionException(String message){
        super(message);
    }
}
