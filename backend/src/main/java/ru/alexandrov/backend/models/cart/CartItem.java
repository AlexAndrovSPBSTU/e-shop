package ru.alexandrov.backend.models.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;
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
public class CartItem implements Persistable<CartItemId> {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnore
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id")
    @JsonIgnore
    @Id
    private Purchase purchase;


    @Column(name = "total_count")
    private int totalCount;

    @Transient
    @JsonIgnore
    private boolean isNew = true;

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    @Override
    @JsonIgnore
    public CartItemId getId() {
        return CartItemId.builder().customer(customer).purchase(purchase).product(product).build();
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Product getProductObject() {
        return product;
    }

    public int getProduct() {
        return product.getId();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", totalCount=" + totalCount +
                '}';
    }
}
