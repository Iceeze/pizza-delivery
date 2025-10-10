package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private List<Product> products = Arrays.asList(
            // Пиццы
            new Product(1L, "Классическая", "Вкусная классическая пицца с томатным соусом и сыром", 1L, 450.0, "/images/classic.avif"),
            new Product(2L, "Домашняя", "Пицца как у мамы - сытная и ароматная", 1L, 520.0, "/images/domash.avif"),
            new Product(3L, "Охотничья", "Для настоящих ценителей мяса и охотничьих колбасок", 1L, 580.0, "/images/ohot.avif"),
            new Product(4L, "4 сыра", "Нежное сочетание четырех видов изысканных сыров", 1L, 490.0, "/images/sirnaya.avif"),

            // Соусы
            new Product(5L, "Кетчуп", "Классический томатный кетчуп", 2L, 50.0, "/images/ket.avif"),
            new Product(6L, "Сырный", "Нежный сырный соус", 2L, 70.0, "/images/sirn.avif"),
            new Product(7L, "Кисло-сладкий", "Пикантный кисло-сладкий соус", 2L, 60.0, "/images/kisl-slad.avif"),
            new Product(8L, "Чесночный", "Ароматный чесночный соус", 2L, 55.0, "/images/chesn.avif"),

            // Напитки
            new Product(9L, "Чай черный Rich Tea 0.5", "Освежающий черный чай", 3L, 80.0, "/images/chern-tea.avif"),
            new Product(10L, "Чай зеленый Rich Tea 0.5", "Тонизирующий зеленый чай", 3L, 80.0, "/images/green-tea.avif"),
            new Product(11L, "Добрый Кола 0.5", "Классическая кола", 3L, 90.0, "/images/dobr.avif"),
            new Product(12L, "Добрый Кола без сахара 0.5", "Кола без сахара", 3L, 90.0, "/images/dobr-nosugar.avif")
    );

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return products.stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}