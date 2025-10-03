package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.model.CartItem;
import com.dmkcompany.pizza.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public void addToCart(Long productId, Integer quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getBasePrice(),
                    quantity,
                    product.getImageUrl()
            );
            cart.addItem(cartItem);
        }
    }

    public void removeFromCart(Long productId) {
        cart.removeItem(productId);
    }

    public void updateQuantity(Long productId, Integer quantity) {
        cart.updateQuantity(productId, quantity);
    }

    public void clearCart() {
        cart.clear();
    }
}