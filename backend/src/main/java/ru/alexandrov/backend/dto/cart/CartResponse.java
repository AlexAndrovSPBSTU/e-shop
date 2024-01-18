package ru.alexandrov.backend.dto.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CartResponse {
    @Getter
    private List<CartItemResponse> products;
    private Integer totalCount;
    private Double totalPrice;

    public Integer getTotalCount() {
        return totalCount == 0 ? null : totalCount;
    }

    public Double getTotalPrice() {
        return totalCount == 0 ? null : totalPrice;
    }
}
