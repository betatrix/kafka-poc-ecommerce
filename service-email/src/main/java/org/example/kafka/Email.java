package org.example.kafka;

public class Email {
    private final String userId, subject, body;

    public Email(String userId, String subject, String body) {
        this.userId = userId;
        this.subject = subject;
        this.body = body;
    }
}
