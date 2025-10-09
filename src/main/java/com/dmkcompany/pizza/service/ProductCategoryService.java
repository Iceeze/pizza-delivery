package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCategoryService {

    private List<ProductCategory> categories = Arrays.asList(
            new ProductCategory(1L, "пицца", "Вкусные пиццы на любой вкус"),
            new ProductCategory(2L, "соусы", "Соусы для пиццы и не только"),
            new ProductCategory(3L, "напитки", "Напитки к вашей пицце")
    );

    public List<ProductCategory> getAllCategories() {
        return categories;
    }

    public ProductCategory getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
