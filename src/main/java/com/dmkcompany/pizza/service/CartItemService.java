package com.dmkcompany.pizza.service;

import com.dmkcompany.pizza.model.CartItem;
import com.dmkcompany.pizza.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ProductService productService;

    public CartItem createCartItemFromProduct(Long productId, Integer quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getBasePrice(),
                    quantity,
                    product.getImageUrl()
            );
        }
        return null;
    }
}
