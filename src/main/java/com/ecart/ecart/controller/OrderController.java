package com.ecart.ecart.controller;

import com.ecart.ecart.dto.CreateOrderRequest;
import com.ecart.ecart.entity.Order;
import com.ecart.ecart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
        @PostMapping
        public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest) {
            // Implementation for creating an order
            Order order = orderService.createOrder(orderRequest);
            return ResponseEntity.ok().body(order);
        }
}
