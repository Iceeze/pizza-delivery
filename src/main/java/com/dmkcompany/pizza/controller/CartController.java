    package com.dmkcompany.pizza.controller;

    import com.dmkcompany.pizza.facade.OrderFacade;
    import com.dmkcompany.pizza.model.Cart;
    import com.dmkcompany.pizza.model.CartItem;
    import com.dmkcompany.pizza.service.CartItemService;
    import com.dmkcompany.pizza.service.CartService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @Controller
    @RequestMapping("/cart")
    @RequiredArgsConstructor
    public class CartController {

        private final CartService cartService;
        private final CartItemService cartItemService;
        private final OrderFacade orderFacade;

        @GetMapping
        public String viewCart(Model model) {
            Cart cart = cartService.getCart();

            if (model.containsAttribute("showOrderSuccess")) {
                return "cart";
            }

            Double totalAmount = cart.getTotalAmount();
            Double finalAmount = orderFacade.calculateFinalAmount(cart);
            Double discount = orderFacade.calculateTotalDiscount(cart);

            Double roundedTotalAmount = (double) Math.round(totalAmount);
            Double roundedFinalAmount = (double) Math.round(finalAmount);
            Double roundedDiscount = (double) Math.round(discount);

            model.addAttribute("cart", cart);
            model.addAttribute("totalAmount", roundedTotalAmount);
            model.addAttribute("discount", roundedDiscount);
            model.addAttribute("finalAmount", roundedFinalAmount);
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
        public String checkout(RedirectAttributes redirectAttributes) {
            try {
                var orderResult = orderFacade.placeOrder();

                redirectAttributes.addFlashAttribute("showOrderSuccess", true);
                redirectAttributes.addFlashAttribute("orderNumber", orderResult.orderNumber());
                redirectAttributes.addFlashAttribute("finalAmount", orderResult.finalAmount());
                redirectAttributes.addFlashAttribute("successMessage", orderResult.message());

                return "redirect:/cart?success";
            } catch (IllegalStateException e) {
                redirectAttributes.addFlashAttribute("showOrderError", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:/cart?error";
            }
        }
    }