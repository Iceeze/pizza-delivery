package com.dmkcompany.pizza.decorator;

public class NewProductDecorator extends ProductBadgeDecorator {

    public NewProductDecorator(ProductDecorator product) {
        super(product);
    }

    @Override
    public String getBadge() {
        return "Новинка";
    }
}