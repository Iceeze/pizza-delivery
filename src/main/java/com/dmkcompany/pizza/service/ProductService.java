package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getProductsByCategory(Long categoryId);
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
