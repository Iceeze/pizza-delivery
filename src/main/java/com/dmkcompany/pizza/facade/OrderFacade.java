package com.dmkcompany.pizza.facade;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final CartService cartService;

    /**
     * Фасадный метод для оформления заказа
     * Скрывает сложную логику процесса оформления заказа
     */
    public OrderResult placeOrder() {
        Cart cart = cartService.getCart();

        validateOrder(cart);
        Double finalAmount = calculateFinalAmount(cart);
        String orderNumber = createOrder(cart, finalAmount);
        OrderResult orderResult = new OrderResult(orderNumber, finalAmount, "Заказ успешно оформлен!");
        cartService.clearCart();

        return orderResult;
    }

    private void validateOrder(Cart cart) {
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Невозможно оформить заказ: корзина пуста");
        }
    }

    public Double calculateFinalAmount(Cart cart) {
        Double baseAmount = cart.getTotalAmount();
        double discountRate = calculateDiscountRate(cart);
        Double finalAmount = baseAmount * (1 - discountRate);
        return (double) Math.round(finalAmount);
    }

    public Double calculateTotalDiscount(Cart cart) {
        Double baseAmount = cart.getTotalAmount();
        double discountRate = calculateDiscountRate(cart);
        Double discount = baseAmount * discountRate;
        return (double) Math.round(discount);
    }

    private double calculateDiscountRate(Cart cart) {
        double discountRate = 0.0;
        Double baseAmount = cart.getTotalAmount();

        // Скидка 10% при заказе от 1000 рублей
        if (baseAmount >= 1000) {
            discountRate += 0.10;
        }

        // Скидка 5% при заказе от 5 товаров
        if (cart.getTotalItems() >= 5) {
            discountRate += 0.05;
        }

        return discountRate;
    }

    private String createOrder(Cart cart, Double finalAmount) {
        String orderNumber = "ORD-" + System.currentTimeMillis();

        System.out.println("Создан заказ #" + orderNumber);
        System.out.println("Товары: " + cart.getItems().size());
        System.out.println("Итоговая сумма: " + finalAmount + " руб.");

        return orderNumber;
    }

    /**
     * DTO для возврата результата оформления заказа
     */
    public record OrderResult(String orderNumber, Double finalAmount, String message) {}
}