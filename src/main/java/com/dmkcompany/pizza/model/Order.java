package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private String status; // новый, подтвержден, готовится, в_дороге, доставлен, отменен
    private Double totalAmount;
    private String paymentMethod; // карта, наличка
    private String paymentStatus;
    private Long addressId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long courierId;
    private String deliveryNotes;
}