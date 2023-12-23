package ru.alexandrov.backend.models.cart;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private int productId;
    private String name;
    private String description;
    private int totalCount;
    private String img;
    private int amount;
    private double price;
    private int discount;
}