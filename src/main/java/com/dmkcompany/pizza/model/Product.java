package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Cloneable {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Double basePrice;
    private String imageUrl;

    public Product createVariant(String newName, String newDescription, Double newPrice) {
        try {
            Product variant = (Product) this.clone();
            variant.setId(null); // Новый ID при сохранении
            variant.setName(newName);
            variant.setDescription(newDescription);
            variant.setBasePrice(newPrice);
            return variant;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Ошибка клонирования продукта");
        }
    }
}