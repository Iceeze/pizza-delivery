package com.dmkcompany.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    private Long id;
    private String name; // пицца, напитки, соусы
    private String description;
}