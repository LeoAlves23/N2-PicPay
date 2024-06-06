package com.picpay.picpay.notification;

/**
 * Exceção lançada quando ocorre um erro relacionado à notificação.
 */
public class NotificationException extends RuntimeException {

    /**
     * Construtor da NotificationException.
     * @param message A mensagem de erro.
     */
    public NotificationException(String message) {
        super(message);
    }

}
