package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.cart.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
