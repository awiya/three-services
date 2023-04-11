package io.awiya.order.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
