package com.picpay.picpay.authorization;

/**
 * Exceção lançada quando uma transação não é autorizada.
 */
public class UnauthorizedTransactionException extends RuntimeException {

    /**
     * Construtor da UnauthorizedTransactionException.
     * @param message A mensagem de erro.
     */
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
