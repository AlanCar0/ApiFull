package com.apifull.controller;

import com.apifull.model.Order;
import com.apifull.security.CustomUserDetails;
import com.apifull.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public Order checkout(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return orderService.checkout(user.getId());
    }
}
