package com.dmkcompany.pizza.factory;

public interface Notification {
    void send(NotificationMessage message);
    boolean supports(NotificationType type);
}