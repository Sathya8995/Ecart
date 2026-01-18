package com.ecart.ecart.service;

import com.ecart.ecart.entity.Product;
import com.ecart.ecart.repository.ProductRepository;
import com.ecart.ecart.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository; //Bean

    public Map<String, Object> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("products", products.getContent());
        response.put("currentPage", products.getNumber());
        response.put("totalItems", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        return response;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found in id: " + id));
    }

    public List<Product> SearchProducts(String category, Double minPrice, Double maxPrice, String Keyword, Double minRating) {
        // Filtering logic to be implemented
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category));
        return productRepository.findAll(spec);
    }
}