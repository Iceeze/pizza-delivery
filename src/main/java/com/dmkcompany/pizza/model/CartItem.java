package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private String imageUrl;

    public Double getTotalPrice() {
        return price * quantity;
    }
}