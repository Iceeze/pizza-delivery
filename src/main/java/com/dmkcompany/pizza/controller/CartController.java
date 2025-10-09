package com.dmkcompany.pizza.controller;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.model.CartItem;
import com.dmkcompany.pizza.service.CartItemService;
import com.dmkcompany.pizza.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    @GetMapping
    public String viewCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", cart.getTotalAmount());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") Integer quantity) {
        CartItem cartItem = cartItemService.createCartItemFromProduct(productId, quantity);
        cartService.addItem(cartItem);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId) {
        CartItem cartItem = cartService.getCartItemByProductId(productId);
        cartService.removeItem(cartItem);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId,
                                 @RequestParam Integer quantity) {
        CartItem cartItem = cartService.getCartItemByProductId(productId);
        try {
            cartService.updateQuantity(cartItem, quantity);
        } catch (NullPointerException e) {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout() {
        cartService.clearCart();
        return "redirect:/cart?success";
    }
}