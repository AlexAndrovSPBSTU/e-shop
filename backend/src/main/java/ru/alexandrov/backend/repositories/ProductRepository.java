package ru.alexandrov.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Product;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    Page<Product> findAllByCategoryIn(Pageable pageable, Collection<Category> category);

    Page<Product> findAllByCategoryInAndPriceBetween(Pageable pageable, Collection<Category> category, Double from, Double to);


}
