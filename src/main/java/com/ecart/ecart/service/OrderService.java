package com.ecart.ecart.service;

import com.ecart.ecart.dto.CreateOrderRequest;
import com.ecart.ecart.dto.OrderCreated;
import com.ecart.ecart.dto.OrderItemDto;
import com.ecart.ecart.entity.Order;
import com.ecart.ecart.entity.OrderItem;
import com.ecart.ecart.entity.Product;
import com.ecart.ecart.repository.OrderRepository;
import com.ecart.ecart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private OrderRepository orderRepo;

    public OrderCreated createOrder(CreateOrderRequest orderRequest) {
        // Implementation for creating an order
        Order order = new Order();
        order.setStatus("PENDING");
        Double totalItemsAmount = 0.0;

        for(OrderItemDto item: orderRequest.getOrderItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setImage(item.getImage());
            orderItem.setQuantity(item.getQuantity());

            orderItem.setProduct(prodRepo.findById(item.getDbId()).orElseThrow(() -> new RuntimeException("Product not found")));
            totalItemsAmount += item.getPrice() * item.getQuantity();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalItemsAmount(totalItemsAmount);
        Double totalAmount = 0.0;
        Double taxAmount = 10.0;
        totalAmount = totalItemsAmount + taxAmount;
        order.setTotalAmount(totalAmount);
        order.setTaxAmount(taxAmount);
        String refId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setReferenceId(refId);
        orderRepo.save(order);
        return new OrderCreated(refId);

    }

    public Order getOrder(String referenceId) {
        return orderRepo.findByReferenceId(referenceId).orElseThrow(() -> new RuntimeException("Order not found in id: " + referenceId));

    }
}
