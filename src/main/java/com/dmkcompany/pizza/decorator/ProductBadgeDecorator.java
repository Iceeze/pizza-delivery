package com.dmkcompany.pizza.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ProductBadgeDecorator implements ProductDecorator {
    protected ProductDecorator decoratedProduct;

    @Override
    public String getName() {
        return decoratedProduct.getName();
    }

    @Override
    public String getDescription() {
        return decoratedProduct.getDescription();
    }

    @Override
    public Double getPrice() {
        return decoratedProduct.getPrice();
    }

    @Override
    public String getImageUrl() {
        return decoratedProduct.getImageUrl();
    }

    @Override
    public Long getId() {
        return decoratedProduct.getId();
    }

    @Override
    public String getBadge() {
        return decoratedProduct.getBadge();
    }
}