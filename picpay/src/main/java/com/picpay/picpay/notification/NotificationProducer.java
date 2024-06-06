package com.picpay.picpay.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.picpay.picpay.transaction.Transaction;

/**
 * Um serviço para produção e envio de notificações de transações.
 */
@Service
public class NotificationProducer {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    /**
     * Construtor do NotificationProducer.
     * @param kafkaTemplate O KafkaTemplate para enviar notificações.
     */
    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Envia uma notificação de transação.
     * @param transaction A transação para a qual a notificação será enviada.
     */
    public void sendNotification(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
