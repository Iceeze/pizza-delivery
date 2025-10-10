package com.dmkcompany.pizza.service.proxy;

import com.dmkcompany.pizza.model.Product;
import com.dmkcompany.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Primary
@RequiredArgsConstructor
public class ProductServiceLoggingProxy implements ProductService {

    @Qualifier("productServiceImpl")
    private final ProductService realProductService;

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        long startTime = System.currentTimeMillis();
        log.info("📊 ЗАПРОС КАТЕГОРИИ: Запрос продуктов категории {}", categoryId);

        try {
            List<Product> products = realProductService.getProductsByCategory(categoryId);
            long duration = System.currentTimeMillis() - startTime;

            log.info("✅ КАТЕГОРИЯ {}: Найдено {} продуктов (время: {} мс)",
                    categoryId, products.size(), duration);

            if (!products.isEmpty()) {
                String popularProducts = products.stream()
                        .limit(3)
                        .map(Product::getName)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                log.info("🏆 Популярные в категории {}: {}", categoryId, popularProducts);
            }

            return products;
        } catch (Exception e) {
            log.error("❌ ОШИБКА КАТЕГОРИИ {}: {}", categoryId, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        long startTime = System.currentTimeMillis();
        log.info("📊 ЗАПРОС ВСЕХ ПРОДУКТОВ: Запрос всех продуктов");

        try {
            List<Product> products = realProductService.getAllProducts();
            long duration = System.currentTimeMillis() - startTime;

            log.info("✅ ВСЕ ПРОДУКТЫ: Загружено {} продуктов (время: {} мс)",
                    products.size(), duration);

            long pizzaCount = products.stream().filter(p -> p.getCategoryId() == 1L).count();
            long sauceCount = products.stream().filter(p -> p.getCategoryId() == 2L).count();
            long drinkCount = products.stream().filter(p -> p.getCategoryId() == 3L).count();

            log.info("📈 СТАТИСТИКА: Пиццы: {}, Соусы: {}, Напитки: {}",
                    pizzaCount, sauceCount, drinkCount);

            return products;
        } catch (Exception e) {
            log.error("❌ ОШИБКА ВСЕХ ПРОДУКТОВ: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Product getProductById(Long id) {
        long startTime = System.currentTimeMillis();
        log.info("📊 ЗАПРОС ПРОДУКТА: Запрос продукта с ID {}", id);

        try {
            Product product = realProductService.getProductById(id);
            long duration = System.currentTimeMillis() - startTime;

            if (product != null) {
                log.info("✅ ПРОДУКТ {}: '{}' - {} руб. (время: {} мс)",
                        id, product.getName(), product.getBasePrice(), duration);
            } else {
                log.warn("⚠️ ПРОДУКТ {}: Не найден (время: {} мс)", id, duration);
            }

            return product;
        } catch (Exception e) {
            log.error("❌ ОШИБКА ПРОДУКТА {}: {}", id, e.getMessage());
            throw e;
        }
    }

    public void logProductAccessStatistics() {
        log.info("📈 СТАТИСТИКА ДОСТУПА: Прокси активен, логирование работает");
    }
}
