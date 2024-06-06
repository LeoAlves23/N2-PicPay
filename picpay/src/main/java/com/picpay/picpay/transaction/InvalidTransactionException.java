package com.picpay.picpay.transaction;

/**
 * Exceção lançada quando ocorre uma transação inválida.
 */
public class InvalidTransactionException extends RuntimeException {
    
    /**
     * Construtor da InvalidTransactionException.
     * @param message A mensagem de erro.
     */
    public InvalidTransactionException(String message) {
        super(message);
    }

}
