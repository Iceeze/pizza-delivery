package com.dmkcompany.pizza.controller;

import com.dmkcompany.pizza.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Передаем первые 2 пиццы для главной страницы
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