package com.dmkcompany.pizza.facade;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.model.Order;
import com.dmkcompany.pizza.service.CartService;
import com.dmkcompany.pizza.service.NotificationService;
import com.dmkcompany.pizza.factory.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final CartService cartService;
    private final NotificationService notificationService;

    /**
     * Фасадный метод для оформления заказа
     * Скрывает сложную логику процесса оформления заказа
     */
    public OrderResult placeOrder(String deliveryAddress, String paymentMethod, String comment,
                                  String customerEmail, String customerPhone)  {
        Cart cart = cartService.getCart();

        validateOrder(cart);

        Double discount = calculateTotalDiscount(cart);
        Double finalAmount = calculateFinalAmount(cart);

        Order order = Order.builder()
                .cart(cart)
                .deliveryAddress(deliveryAddress)
                .paymentMethod(paymentMethod)
                .comment(comment)
                .discount(discount)
                .finalAmount(finalAmount)
                .build();

        saveOrder(order);
        sendOrderNotifications(order, customerEmail, customerPhone);

        OrderResult orderResult = new OrderResult(
                order.getOrderNumber(),
                order.getFinalAmount(),
                "Заказ успешно оформлен!"
        );
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

    private void saveOrder(Order order) {
        System.out.println("=== СОЗДАН ЗАКАЗ ===");
        System.out.println("Номер: " + order.getOrderNumber());
        System.out.println("Адрес: " + order.getDeliveryAddress());
        System.out.println("Способ оплаты: " + order.getPaymentMethod());
        System.out.println("Комментарий: " + order.getComment());
        System.out.println("Товаров: " + order.getCart().getTotalItems());
        System.out.println("Общая сумма: " + order.getTotalAmount() + " руб.");
        System.out.println("Скидка: " + order.getDiscount() + " руб.");
        System.out.println("Итоговая сумма: " + order.getFinalAmount() + " руб.");
        System.out.println("Статус: " + order.getStatus());
        System.out.println("====================");
    }

    private void sendOrderNotifications(Order order, String email, String phone) {
        try {
            if (email != null && !email.trim().isEmpty()) {
                notificationService.sendOrderNotification(
                        NotificationType.EMAIL,
                        email,
                        order.getOrderNumber(),
                        order.getFinalAmount()
                );
            }

            if (phone != null && !phone.trim().isEmpty()) {
                notificationService.sendOrderNotification(
                        NotificationType.SMS,
                        phone,
                        order.getOrderNumber(),
                        order.getFinalAmount()
                );
            }
        } catch (Exception e) {
            System.err.println("⚠️ Не удалось отправить уведомления: " + e.getMessage());
        }
    }


    /**
     * DTO для возврата результата оформления заказа
     */
    public record OrderResult(String orderNumber, Double finalAmount, String message) {}
}