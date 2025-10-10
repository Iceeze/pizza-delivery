package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.decorator.*;
import com.dmkcompany.pizza.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductBadgeService {

    // Здесь можно хранить логику определения, какой продукт является новинкой или хитом
    // Пока сделаем простую версию с предопределенными ID

    private final Set<Long> newProductIds = Set.of(3L);
    private final Set<Long> bestsellerIds = Set.of(1L, 2L);

    public ProductDecorator decorateProduct(Product product) {
        ProductDecorator decorated = new BasicProduct(product);

        if (newProductIds.contains(product.getId())) {
            decorated = new NewProductDecorator(decorated);
        }

        if (bestsellerIds.contains(product.getId())) {
            decorated = new BestsellerDecorator(decorated);
        }

        return decorated;
    }

    public List<ProductDecorator> decorateProducts(List<Product> products) {
        return products.stream()
                .map(this::decorateProduct)
                .toList();
    }

    public List<ProductDecorator> getProductsWithBadge(List<Product> products, String badge) {
        return products.stream()
                .map(this::decorateProduct)
                .filter(p -> badge.equals(p.getBadge()))
                .collect(Collectors.toList());
    }

    public List<ProductDecorator> getBestsellerProducts(List<Product> products, int limit) {
        return products.stream()
                .map(this::decorateProduct)
                .filter(p -> "Хит продаж".equals(p.getBadge()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}