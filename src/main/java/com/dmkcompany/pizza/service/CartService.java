package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartService {
    private Cart cart;

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

    public void addItem(CartItem cartItem) {
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            if (item.getProductId().equals(cartItem.getProductId())) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        items.add(cartItem);
    }

    public void removeItem(CartItem cartItem) {
        List<CartItem> items = cart.getItems();
        items.remove(cartItem);
    }

    public void updateQuantity(CartItem cartItem, Integer quantity) {
        if (cartItem == null) {
            throw new NullPointerException("Элемента корзины не существует.");
        }
        cartItem.setQuantity(quantity);
    }

    public CartItem getCartItemByProductId(Long productId) {
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    public void clearCart() {
        List<CartItem> items = cart.getItems();
        items.clear();
    }
}