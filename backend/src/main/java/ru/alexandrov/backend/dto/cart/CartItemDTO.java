package ru.alexandrov.backend.dto.cart;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Integer productId;
    private Integer totalCount;
}
