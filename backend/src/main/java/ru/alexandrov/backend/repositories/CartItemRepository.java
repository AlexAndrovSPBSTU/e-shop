package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.CartItemId;
import ru.alexandrov.backend.models.Customer;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findAllByCustomer(Customer customer);
}
