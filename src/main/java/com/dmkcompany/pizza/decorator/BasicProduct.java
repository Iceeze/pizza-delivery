package com.dmkcompany.pizza.decorator;

import com.dmkcompany.pizza.model.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasicProduct implements ProductDecorator {
    private Product product;

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    @Override
    public Double getPrice() {
        return product.getBasePrice();
    }

    @Override
    public String getImageUrl() {
        return product.getImageUrl();
    }

    @Override
    public Long getId() {
        return product.getId();
    }

    @Override
    public String getBadge() {
        return null; // Базовая версия без пометки
    }
}