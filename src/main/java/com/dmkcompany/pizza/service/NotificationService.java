package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.factory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationFactory notificationFactory;

    public void sendNotification(NotificationType type, String to, String content) {
        try {
            Notification notification = notificationFactory.createNotification(type);
            NotificationMessage message = new NotificationMessage(to, content, type);

            log.info("🚀 Отправка {} уведомления для: {}", type, to);
            notification.send(message);
            log.info("✅ {} уведомление успешно отправлено", type);

        } catch (Exception e) {
            log.error("❌ Ошибка отправки {} уведомления: {}", type, e.getMessage());
            throw new RuntimeException("Не удалось отправить уведомление", e);
        }
    }

    public void sendOrderNotification(NotificationType type, String to, String orderNumber, Double amount) {
        String content = String.format(
                "Заказ #%s успешно оформлен! Сумма: %.2f руб. Спасибо за ваш заказ! 🍕",
                orderNumber, amount
        );

        sendNotification(type, to, content);
    }

    public void sendOrderStatusNotification(NotificationType type, String to, String orderNumber, String status) {
        String content = String.format(
                "Статус вашего заказа #%s изменен на: %s",
                orderNumber, status
        );

        sendNotification(type, to, content);
    }
}