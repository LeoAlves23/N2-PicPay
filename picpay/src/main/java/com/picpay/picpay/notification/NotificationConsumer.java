package com.picpay.picpay.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.picpay.picpay.transaction.Transaction;

/**
 * Um serviço para consumir notificações de transações.
 */
@Service
public class NotificationConsumer {
    
    private RestClient restClient;

    /**
     * Construtor do NotificationConsumer.
     * @param builder O construtor para o RestClient.
     */
    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
            .baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
            .build();
    }

    /**
     * Método de escuta para receber notificações de transações.
     * @param transaction A transação recebida na notificação.
     * @throws NotificationException Se ocorrer um erro ao enviar a notificação.
     */
    @KafkaListener(topics = "transaction-notification", groupId = "picpay")
    public void receiveNotification(Transaction transaction){

        var response = restClient.get()
            .retrieve()
            .toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message()) {
            throw new NotificationException("Erro ao enviar a notificação!");
        }
    }
}
