package com.dmkcompany.pizza.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void updateQuantity(Long productId, Integer quantity) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public Double getTotalAmount() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public Integer getTotalItems() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void clear() {
        items.clear();
    }
}