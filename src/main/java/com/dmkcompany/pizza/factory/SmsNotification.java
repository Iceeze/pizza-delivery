package com.dmkcompany.pizza.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsNotification implements Notification {

    @Override
    public void send(NotificationMessage message) {
        // В реальном проекте здесь должна быть интеграция с SMS-сервисом

        log.info("📱 ОТПРАВКА SMS:");
        log.info("Номер: {}", formatPhoneNumber(message.getTo()));
        log.info("Сообщение: {}", truncateMessage(message.getContent()));
        log.info("--- SMS отправлено успешно ---");

        simulateNetworkDelay();
    }

    @Override
    public boolean supports(NotificationType type) {
        return type == NotificationType.SMS;
    }

    private String formatPhoneNumber(String phone) {
        return phone.replaceAll("(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "+$1 ($2) $3-$4-$5");
    }

    private String truncateMessage(String message) {
        return message.length() > 160 ? message.substring(0, 157) + "..." : message;
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
