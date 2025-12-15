package com.apifull.service;

import com.apifull.dto.CartDto;
import com.apifull.dto.CartItemDto;
import com.apifull.model.Cart;
import com.apifull.model.CartItem;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDto toDto(Cart cart) {

        List<CartItemDto> items = cart.getItems()
                .stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        return CartDto.builder()
                .cartId(cart.getId())
                .items(items)
                .total(total)
                .build();
    }

    private CartItemDto toItemDto(CartItem item) {
        return CartItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .price(item.getProduct().getPrice())
                .build();
    }
}
