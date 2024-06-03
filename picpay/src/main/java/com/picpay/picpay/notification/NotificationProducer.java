package com.picpay.picpay.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.picpay.picpay.transaction.Transaction;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
      }

      public void sendNotification(Transaction transaction){
        kafkaTemplate.send("transactoin-notification", transaction);
      }
}
