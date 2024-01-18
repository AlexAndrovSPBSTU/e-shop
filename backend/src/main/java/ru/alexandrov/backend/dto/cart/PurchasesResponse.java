package ru.alexandrov.backend.dto.cart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasesResponse {
    private String date;
    private List<CartItemDTO> products;
}
