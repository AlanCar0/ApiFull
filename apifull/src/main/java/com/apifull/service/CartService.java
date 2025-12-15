package com.apifull.service;

import com.apifull.model.*;
import com.apifull.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow();
                    Cart cart = Cart.builder()
                            .user(user)
                            .build();
                    return cartRepository.save(cart);
                });
    }

    public Cart addProduct(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        Products product = productRepository.findById(productId)
                .orElseThrow();

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cart;
            }
        }

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .price(product.getPrice())
                .build();

        cart.getItems().add(item);
        return cart;
    }

    public Cart removeItem(Long userId, Long itemId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(i -> i.getId().equals(itemId));
        return cart;
    }

    public void clearCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().clear();
    }
}