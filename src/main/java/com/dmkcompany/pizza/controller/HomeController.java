package com.dmkcompany.pizza.controller;

import com.dmkcompany.pizza.model.Cart;
import com.dmkcompany.pizza.service.CartService;
import com.dmkcompany.pizza.service.ProductBadgeService;
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
    private final ProductBadgeService productBadgeService;
    private final CartService cartService;

    @ModelAttribute("cart")
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping("/")
    public String home(Model model) {
        var allPizzas = productService.getProductsByCategory(1L);
        var bestsellerPizzas = productBadgeService.getBestsellerProducts(allPizzas, 2);

        model.addAttribute("featuredPizzas", bestsellerPizzas);
        return "index";
    }

    @GetMapping("/pizzas")
    public String pizzas(Model model) {
        var products = productService.getProductsByCategory(1L);
        var decoratedProducts = productBadgeService.decorateProducts(products);

        model.addAttribute("pizzas", decoratedProducts);
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