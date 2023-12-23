package ru.alexandrov.backend.models.cart;

import lombok.*;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemId implements Serializable {
    private Customer customer;
    private Product product;
    private Purchase purchase;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartItemId that = (CartItemId) o;
        return Objects.equals(customer, that.customer) && Objects.equals(product, that.product) && Objects.equals(purchase, that.purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product, purchase);
    }
}
