package com.ecart.ecart.service;

import com.ecart.ecart.dto.ProductDto;
import com.ecart.ecart.dto.ProductReviewDto;
import com.ecart.ecart.dto.ProductImageDto;
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
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository; //Bean

    public Map<String, Object> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDto> productDtos = products.stream().map( this::convertToDto ).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("products", productDtos);
        response.put("currentPage", products.getNumber());
        response.put("totalItems", products.getTotalElements());
        response.put("totalPages", products.getTotalPages());
        return response;
    }

    public ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setRatings(product.getRatings());
        dto.setCategory(product.getCategory());
        dto.setSeller(product.getSeller());
        dto.setStock(product.getStock());
        dto.setNumOfReviews(product.getNumOfReviews());

        List<ProductReviewDto>  reviewDtos = product.getReviews().stream().map(review -> {
            ProductReviewDto reviewDto = new ProductReviewDto();
            reviewDto.setProductId(review.getId());
            reviewDto.setComment(review.getComment());
            reviewDto.setRating(review.getRating());
            return reviewDto;
        }).collect(Collectors.toList());

        dto.setReviews(reviewDtos);

        List<ProductImageDto>  imageDtos = product.getImages().stream().map(image -> {
            ProductImageDto imageDto = new ProductImageDto(image.getPublicId());
            return imageDto;
        }).collect(Collectors.toList());

        dto.setImages(imageDtos);
        return dto;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found in id: " + id));
    }

    public List<Product> SearchProducts(String category, Double minPrice, Double maxPrice, String Keyword, Double minRating) {
        // Filtering logic to be implemented
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice))
                .and(ProductSpecification.hasKeyword(Keyword))
                .and(ProductSpecification.hasMinRating(minRating));
        return productRepository.findAll(spec);
    }
}