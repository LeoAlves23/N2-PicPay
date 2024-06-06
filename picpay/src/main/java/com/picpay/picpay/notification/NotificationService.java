package com.picpay.picpay.notification;

import org.springframework.stereotype.Service;

import com.picpay.picpay.transaction.Transaction;

/**
 * Um serviço para notificação de transações.
 */
@Service
public class NotificationService {
    
    /**
     * Notifica sobre uma transação.
     * @param transaction A transação para a qual a notificação será enviada.
     */
    public void notify(Transaction transaction) {
        // Implemente aqui a lógica de notificação
    }
}
