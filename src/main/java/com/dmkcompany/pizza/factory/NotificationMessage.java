package com.dmkcompany.pizza.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String to;
    private String subject;
    private String content;
    private NotificationType type;

    public NotificationMessage(String to, String content, NotificationType type) {
        this.to = to;
        this.content = content;
        this.type = type;
        this.subject = "Уведомление от Pizza Delivery";
    }
}
