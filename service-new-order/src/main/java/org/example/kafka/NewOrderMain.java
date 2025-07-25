package org.example.kafka;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var emailDispatcher = new KafkaDispatcher<Email>()) {
            try (var orderDispatcher = new KafkaDispatcher<Order>()) {
                for (int i = 0; i < 10; i++) {
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);
                    var order = new Order(userId, orderId, amount);
                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    var subject = "Hey there!";
                    var body = "Thank you for your order! We are processing your order";
                    var emailId =  UUID.randomUUID().toString();
                    var email = new Email(userId, subject, body);
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }
        }
    }
}