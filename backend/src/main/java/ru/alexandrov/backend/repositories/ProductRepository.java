package ru.alexandrov.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
}