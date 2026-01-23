package com.ecart.ecart.dto;

public class OrderItemDto {
    private String name;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        // Unknown image
        return switch (this.getName()) {
            case "Iphone 15" -> "/products/IPhone-15.jpg";
            case "Samsung Galaxy S24" -> "/products/GalaxyS24.jpg";
            case "Google Pixel 8" -> "/products/Pixel8.jpeg";
            case "OnePlus 12" -> "/products/Oneplus12.jpg";
            case "Sony WH-1000XM5" -> "/products/Sonyheadphone.jpg";
            case "Apple AirPods Pro 2" -> "/products/airpodspro2.webp";
            case "Dell XPS 13" -> "/products/DellLaptop.jpg";
            case "Samsung 55\" QLED TV" -> "/products/SamsungTV.jpg";
            case "Nintendo Switch OLED" -> "/products/NSwitch.jpg";
            case "Kindle Paperwhite" -> "/products/Kindle.jpg";
            default -> "/products/unknown.jpg";
        };
    }

    public Long getDbId() {
        // Unknown product
        return switch (this.getName()) {
            case "Iphone 15" -> 1L;
            case "Samsung Galaxy S24" -> 2L;
            case "Google Pixel 8" -> 3L;
            case "OnePlus 12" -> 4L;
            case "Sony WH-1000XM5" -> 5L;
            case "Apple AirPods Pro 2" -> 6L;
            case "Dell XPS 13" -> 7L;
            case "Samsung 55\" QLED TV" -> 8L;
            case "Nintendo Switch OLED" -> 9L;
            case "Kindle Paperwhite" -> 10L;
            default -> 0L;
        };
    }

    public Double getPrice() {
        // Unknown price
        return switch (this.getName()) {
            case "Iphone 15" -> 799.0;
            case "Samsung Galaxy S24" -> 749.0;
            case "Google Pixel 8" -> 699.0;
            case "OnePlus 12" -> 649.0;
            case "Sony WH-1000XM5", "Nintendo Switch OLED" -> 349.0;
            case "Apple AirPods Pro 2" -> 249.0;
            case "Dell XPS 13" -> 999.0;
            case "Samsung 55\" QLED TV" -> 899.0;
            case "Kindle Paperwhite" -> 129.0;
            default -> 0.0;
        };
    }
}
