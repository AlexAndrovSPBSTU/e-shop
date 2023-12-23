package ru.alexandrov.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.cart.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
