package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Customer;
import ru.alexandrov.backend.models.cart.CartItem;
import ru.alexandrov.backend.models.cart.CartItemId;
import ru.alexandrov.backend.models.cart.Purchase;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, CartItemId> {

    @Modifying
    @Query(value = "update cart_item set purchase_id=:purchaseId where product_id=:productId and customer_id=:customerId and purchase_id=-1", nativeQuery = true)
    void updatePurchase(@Param("customerId") int customerId, @Param("productId") int productId, @Param("purchaseId") int purchaseId);

    List<CartItem> findAllByCustomerAndPurchase(Customer customer, Purchase purchase);
}
