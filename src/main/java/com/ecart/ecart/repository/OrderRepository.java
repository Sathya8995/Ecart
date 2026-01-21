package com.ecart.ecart.repository;

import com.ecart.ecart.entity.Order;
import com.ecart.ecart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Product> {
}
