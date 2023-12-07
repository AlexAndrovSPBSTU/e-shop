package ru.alexandrov.backend.util;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum ProductSorting {
    BY_PRICE_ASCENDING(Sort.Direction.ASC, "price"),
    BY_PRICE_DESCENDING(Sort.Direction.DESC, "price"),
    BY_RATING(Sort.Direction.DESC, "rating"),
    BY_DISCOUNT(Sort.Direction.DESC, "discount");


    private final Sort.Direction direction;
    private final String property;

    ProductSorting(Sort.Direction direction, String property) {
        this.direction = direction;
        this.property = property;
    }
}
