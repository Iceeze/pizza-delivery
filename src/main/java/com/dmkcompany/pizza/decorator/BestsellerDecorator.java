package com.dmkcompany.pizza.decorator;

public class BestsellerDecorator extends ProductBadgeDecorator {

    public BestsellerDecorator(ProductDecorator product) {
        super(product);
    }

    @Override
    public String getBadge() {
        return "Хит продаж";
    }
}