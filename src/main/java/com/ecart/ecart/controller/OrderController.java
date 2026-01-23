package com.ecart.ecart.controller;

import com.ecart.ecart.dto.CreateOrderRequest;
import com.ecart.ecart.dto.OrderCreated;
import com.ecart.ecart.entity.Order;
import com.ecart.ecart.entity.Product;
import com.ecart.ecart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
        @PostMapping
        public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest orderRequest) {
            // Implementation for creating an order
            OrderCreated order = orderService.createOrder(orderRequest);
            return ResponseEntity.ok().body(order);
        }


    @GetMapping("/{referenceId}")
    public ResponseEntity<?> getOrder(@PathVariable String referenceId){
        Order order =  orderService.getOrder(referenceId);
        return ResponseEntity.ok().body(order);
    }
}
