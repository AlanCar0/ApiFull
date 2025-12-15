package com.apifull.service;

import com.apifull.model.*;
import com.apifull.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;

    public Order checkout(Long userId) {
        Cart cart = cartService.getOrCreateCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrito vacío");
        }

        double total = 0;
        Order order = Order.builder()
                .user(cart.getUser())
                .createdAt(LocalDateTime.now())
                .status("PAID")
                .build();

        order = orderRepository.save(order);

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productName(item.getProduct().getName())
                    .price(item.getProduct().getPrice())
                    .quantity(item.getQuantity())
                    .build();

            orderItemRepository.save(orderItem);
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        order.setTotal(total);
        cartService.clearCart(userId);

        return orderRepository.save(order);
    }
}