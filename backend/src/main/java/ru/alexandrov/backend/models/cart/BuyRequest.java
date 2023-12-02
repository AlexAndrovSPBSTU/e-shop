package ru.alexandrov.backend.models.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuyRequest {
    List<CartAdditionRequest> products;
}
