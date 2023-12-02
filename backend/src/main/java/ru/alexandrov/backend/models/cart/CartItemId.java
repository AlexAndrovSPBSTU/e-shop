package ru.alexandrov.backend.models.cart;

import lombok.Getter;
import lombok.Setter;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
public class CartItemId implements Serializable {
    private Customer customer;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(customer, that.customer) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product);
    }
}
