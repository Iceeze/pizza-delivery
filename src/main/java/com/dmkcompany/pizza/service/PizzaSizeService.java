package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.Product;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {

    public Product createSizeVariant(Product basePizza, String size) {
        System.out.println("=== PIZZA SIZE SERVICE ===");
        System.out.println("Base pizza: " + basePizza.getName() + ", base price: " + basePizza.getBasePrice());
        System.out.println("Requested size: " + size);

        return switch (size) {
            case "small" -> {
                System.out.println("Small price: " + (basePizza.getBasePrice() * 0.7));
                yield basePizza.createVariant(
                        basePizza.getName() + " (25 см)",
                        basePizza.getDescription() + " Маленькая пицца 25 см",
                        basePizza.getBasePrice() * 0.7
                );
            }
            case "medium" -> {
                System.out.println("Medium price: " + basePizza.getBasePrice());
                yield basePizza.createVariant(
                        basePizza.getName() + " (30 см)",
                        basePizza.getDescription(),
                        basePizza.getBasePrice()
                );
            }
            case "large" -> {
                System.out.println("Large price: " + (basePizza.getBasePrice() * 1.3));
                yield basePizza.createVariant(
                        basePizza.getName() + " (35 см)",
                        basePizza.getDescription() + " Большая пицца 35 см",
                        basePizza.getBasePrice() * 1.3
                );
            }
            default -> basePizza;
        };
    }
}