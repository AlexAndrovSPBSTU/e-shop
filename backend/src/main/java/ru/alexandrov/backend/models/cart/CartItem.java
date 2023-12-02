package ru.alexandrov.backend.models.cart;

import lombok.*;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
@IdClass(CartItemId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "totalcount")
    private int totalCount;

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
}
