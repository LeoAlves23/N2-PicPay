package com.picpay.picpay.authorization;

/**
 * Uma exceção lançada quando uma transação não é autorizada.
 */
public class UnauhotizedTransactionException extends RuntimeException {

    /**
     * Construtor da UnauthorizedTransactionException.
     * @param message A mensagem de erro.
     */
    public UnauhotizedTransactionException(String message) {
        super(message);
    }
}
