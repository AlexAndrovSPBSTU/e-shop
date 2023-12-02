package ru.alexandrov.backend.models.cart;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartAdditionRequest {
    private int id;
    private int totalCount;
}
