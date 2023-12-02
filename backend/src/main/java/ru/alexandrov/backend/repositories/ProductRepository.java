package ru.alexandrov.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    Page<Product> findAllByCategory(Pageable pageable,Category category);
}
