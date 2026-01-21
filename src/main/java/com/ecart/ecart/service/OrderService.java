package com.ecart.ecart.service;

import com.ecart.ecart.dto.CreateOrderRequest;
import com.ecart.ecart.dto.OrderItemDto;
import com.ecart.ecart.entity.Order;
import com.ecart.ecart.entity.OrderItem;
import com.ecart.ecart.entity.Product;
import com.ecart.ecart.repository.OrderRepository;
import com.ecart.ecart.repository.ProductRepository;
import com.ecart.ecart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private OrderRepository orderRepo;

    public Order createOrder(CreateOrderRequest orderRequest) {
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

            Product product = prodRepo.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            totalItemsAmount += item.getPrice() * item.getQuantity();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalItemsAmount(totalItemsAmount);
        Double totalAmount = 0.0;
        Double taxAmount = 10.0;
        totalAmount = totalItemsAmount + taxAmount;
        order.setTotalAmount(totalAmount);
        order.setTaxAmount(taxAmount);
        order.setReferenceId(UUID.randomUUID().toString());
        return orderRepo.save(order);

    }

    public Order getOrder(String referenceId) {
        return orderRepo.findByReferenceId(referenceId).orElseThrow(() -> new RuntimeException("Order not found in id: " + referenceId));

    }
}
