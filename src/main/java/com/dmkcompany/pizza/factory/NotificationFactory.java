package com.dmkcompany.pizza.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationFactory {

    private final List<Notification> notifications;

    public Notification createNotification(NotificationType type) {
        return notifications.stream()
                .filter(notification -> notification.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Неподдерживаемый тип уведомления: " + type));
    }
}
