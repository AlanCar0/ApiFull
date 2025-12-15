package com.apifull.controller;

import com.apifull.dto.CartDto;
import com.apifull.model.Cart;
import com.apifull.security.CustomUserDetails;
import com.apifull.service.CartMapper;
import com.apifull.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping
    public CartDto getCart(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Cart cart = cartService.getOrCreateCart(user.getId());
        return cartMapper.toDto(cart);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<Void> addToCart(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        cartService.addProduct(user.getId(), productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Void> removeItem(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long itemId
    ) {
        cartService.removeItem(user.getId(), itemId);
        return ResponseEntity.ok().build();
    }
}
