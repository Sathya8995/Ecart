package com.ecart.ecart.seed;

import com.ecart.ecart.entity.Product;
import com.ecart.ecart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() == 0){
            List<Product> products = Arrays.asList(
                    new Product(null, "Iphone 15", 799.0, "Latest Apple iPhone 15 with advanced features", "Smartphones", 4.8, "Amazon", 500),
                    new Product(null, "Samsung Galaxy S24", 749.0, "Samsung flagship with high refresh display", "Smartphones", 4.6, "BestBuy", 400),
                    new Product(null, "Google Pixel 8", 699.0, "Google phone with clean Android and great camera", "Smartphones", 4.5, "Google Store", 350),
                    new Product(null, "OnePlus 12", 649.0, "Performance-focused phone with fast charging", "Smartphones", 4.4, "OnePlus", 300),
                    new Product(null, "Sony WH-1000XM5", 349.0, "Top-tier noise cancelling headphones", "Audio", 4.7, "Sony", 200),
                    new Product(null, "Apple AirPods Pro 2", 249.0, "Wireless earbuds with active noise cancellation", "Audio", 4.6, "Apple Store", 450),
                    new Product(null, "Dell XPS 13", 999.0, "Compact premium laptop for professionals", "Laptops", 4.5, "Dell", 120),
                    new Product(null, "Samsung 55\" QLED TV", 899.0, "4K QLED smart TV with vivid colors", "TVs", 4.4, "Samsung", 80),
                    new Product(null, "Nintendo Switch OLED", 349.0, "Portable console with OLED display", "Gaming", 4.8, "Nintendo", 250),
                    new Product(null, "Kindle Paperwhite", 129.0, "Waterproof e-reader with high-resolution display", "E-Readers", 4.7, "Amazon", 600)
            );

            productRepository.saveAll(products);
            System.out.println("Seeded Products Data");
        }
        else {
            System.out.println("Products Data already exists, skipping seeding.");
        }
    }
}
