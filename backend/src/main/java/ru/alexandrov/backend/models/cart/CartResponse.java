package ru.alexandrov.backend.models.cart;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private int productId;
    private int totalCount;
    private String description;
    private String img;
    private int amount;
    private double price;
}