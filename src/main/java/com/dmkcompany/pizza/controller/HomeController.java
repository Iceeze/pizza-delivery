package com.dmkcompany.pizza.controller;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.service.CartService;
import com.dmkcompany.pizza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;

    @ModelAttribute("cart")
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping("/")
    public String home(Model model) {
        var featuredPizzas = productService.getProductsByCategory(1L).subList(0, 2);
        model.addAttribute("featuredPizzas", featuredPizzas);
        return "index";
    }

    @GetMapping("/pizzas")
    public String pizzas(Model model) {
        model.addAttribute("pizzas", productService.getProductsByCategory(1L));
        return "pizzas";
    }

    @GetMapping("/sauces")
    public String sauces(Model model) {
        model.addAttribute("sauces", productService.getProductsByCategory(2L));
        return "sauces";
    }

    @GetMapping("/juices")
    public String juices(Model model) {
        model.addAttribute("drinks", productService.getProductsByCategory(3L));
        return "juices";
    }

    @GetMapping("/reviews")
    public String reviews() {
        return "reviews";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }
}