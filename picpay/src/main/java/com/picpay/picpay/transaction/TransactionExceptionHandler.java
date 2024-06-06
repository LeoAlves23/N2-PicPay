package com.picpay.picpay.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Um manipulador de exceções para transações.
 */
@ControllerAdvice
public class TransactionExceptionHandler {

    /**
     * Manipula a exceção InvalidTransactionException.
     * @param e A exceção InvalidTransactionException a ser tratada.
     * @return Uma resposta HTTP com o status 400 (Bad Request) e a mensagem de erro da exceção.
     */
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handle(InvalidTransactionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
}
