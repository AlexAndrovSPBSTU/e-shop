package ru.alexandrov.backend.models.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDTO {
    private int productId;
    private int totalCount;
    private String description;
    private String img;
    private int amount;
    private double price;
}