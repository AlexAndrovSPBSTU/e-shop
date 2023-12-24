package ru.alexandrov.backend.models.cart;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CheckBeforeBuyResponse {
    private List<Integer> soldOutProducts;
    private List<Integer> nonExistentProducts;
}
