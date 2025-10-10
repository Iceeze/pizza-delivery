package com.dmkcompany.pizza.decorator;

public interface ProductDecorator {
    String getName();
    String getDescription();
    Double getPrice();
    String getImageUrl();
    Long getId();
    String getBadge();
}